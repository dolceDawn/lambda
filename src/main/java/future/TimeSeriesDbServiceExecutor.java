package future;

import java.util.concurrent.*;

/**
 * 客户端开启线程池进行时序数据库的并行数据库操作
 *
 * @author zhanglm
 * @date 2019/1/28
 */
public class TimeSeriesDbServiceExecutor {

    private static final ExecutorService EXECUTOR = new ThreadPoolExecutor(3, 10, 600, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new DaemonThreadFactory());

    public static void execute(Runnable runnable) {
        EXECUTOR.execute(runnable);
    }
    /**
     * 自定义守护线程工厂类
     */
    private static class DaemonThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    }
}
