package aqs;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Desc:
 * @Author: zhangliming
 * @Date: 8/6/2020 3:10
 */
public class SemaphoreDemo {

    public static final ExecutorService EXECUTORSERVICE = Executors.newCachedThreadPool();

    public static void main(String[] args) {

        //允许5个线程同时访问
        Semaphore semaphore = new Semaphore(5);

        for (int i=0; i < 8; i++){
            final long num = i;
            EXECUTORSERVICE.submit(() -> {
                //获取许可
                try {
                    semaphore.acquire();
                    //执行
                    System.out.println("Accessing: " + num);
                    Thread.sleep(new Random().nextInt(5000));

                    System.out.println("Release..." + num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //释放
                    semaphore.release();
                }
            });
        }

        EXECUTORSERVICE.shutdown();
    }

}
