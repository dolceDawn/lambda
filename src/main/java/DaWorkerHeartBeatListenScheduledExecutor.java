
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ly
 * 2019.1.8
 * 心跳发送器监听器，专用于定时的检测da_worker发送的状态信息
 */
public class DaWorkerHeartBeatListenScheduledExecutor {

    /**
     * 初始计数器，用于表示HeartBeatExecutor被使用的次数（HeartBeatExecutor只能被使用一次，否则抛异常结束）
     */
    private static int count = 0;
    /**
     * 默认的延迟执行时间
     */
    private static final int DEAFAULT_INITALDELAY = 0;

    /**
     * 定时任务的默认执行时间单位（秒）
     */
    private static final TimeUnit DEAFAULT_TIMEUNIT = TimeUnit.SECONDS;

    /**
     * 委托定时器
     */
    private static final ScheduledThreadPoolExecutor HEARBEATSERVICE = new ScheduledThreadPoolExecutor(4,new CommonThreadFactory());

    /**
     * 支持默认参数的执行动作
     *
     * @param command
     * @param period
     */
    public synchronized static void execute(Runnable command, int period) {
        execute(command, DEAFAULT_INITALDELAY, period, DEAFAULT_TIMEUNIT);
    }

    /**
     * 支持定制版的执行动作
     *
     * @param command
     * @param initalDelay
     * @param period
     * @param timeUnit
     */
    public synchronized static void execute(Runnable command, int initalDelay, int period, TimeUnit timeUnit) {
        if (count > 1) {
            throw new RuntimeException("HEARBEATSERVICE has been used,can not try it again...");
        }
        count++;
        HEARBEATSERVICE.scheduleAtFixedRate(command, initalDelay, period, timeUnit);
    }


    /**
     * 线程池关闭操作（平缓的关闭）
     */
    public synchronized static void shutdown() {
        if (count == 0) {
            throw new RuntimeException("HEARBEATSERVICE has not been used,can not shutdown it ...");
        }
        count--;
        HEARBEATSERVICE.shutdown();
    }

    /**
     * 线程池关闭操作(强制关闭）
     */
    public synchronized static void shutdownNow() {
        if (count == 0) {
            throw new RuntimeException("HEARBEATSERVICE has not been used,can not shutdown it ...");
        }
        count--;
        HEARBEATSERVICE.shutdownNow();
    }

}
