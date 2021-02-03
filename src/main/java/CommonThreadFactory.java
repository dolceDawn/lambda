
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadFactory;

/**
 * @author yaming.chen@siemens.com
 * Created by chenyaming on 2019/4/3.
 */
public class CommonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setName("siemens-threadpool");
        t.setUncaughtExceptionHandler((t1, e) -> LoggerFactory.getLogger(t1.getName()).error(e.getMessage(), e));
        return t;
    }
}
