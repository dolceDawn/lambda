package aqs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Desc:
 * @Author: zhangliming
 * @Date: 8/6/2020 3:10
 */
public class ResourceByCondition {

    private String name;
    private int count = 1;
    private boolean flag = false;

    /**
     * 创建一个锁对象。
     */
    Lock lock = new ReentrantLock();

    /**
     * 通过已有的锁获取两组监视器，一组监视生产者，一组监视消费者。
     */
    Condition producer_con = lock.newCondition();
    Condition consumer_con = lock.newCondition();

    /**
     * 生产
     * @param name
     */
    public void product(String name) {
        lock.lock();
        try {
            while (flag) {
                try{producer_con.await();}catch(InterruptedException e){}
            }
            this.name = name + count;
            count++;
            try {Thread.sleep(1000);}catch(InterruptedException e){}
            System.out.println(Thread.currentThread().getName()+"...生产者..." + this.name);
            flag = true;
            //唤醒消费线程
            consumer_con.signal();
        }
        finally {
            lock.unlock();
        }
    }

    /**
     * 消费
     */
    public  void consume() {
        lock.lock();
        try {
            while (!flag) {
                try{consumer_con.await();}catch(InterruptedException e){}
            }
            try {Thread.sleep(1000);}catch (InterruptedException e){}
            //消费
            System.out.println(Thread.currentThread().getName()+"...消费者......." + this.name);
            flag = false;
            //唤醒生产线程
            producer_con.signal();
        }
        finally {
            lock.unlock();
        }
    }

}
