package aqs;

/**
 * @Desc:
 * @Author: zhangliming
 * @Date: 8/6/2020 3:10
 */
public class MutilProducerConsumerByCondition {

    public static void main(String[] args) {
        ResourceByCondition r = new ResourceByCondition();
        Producer pro = new Producer(r);
        Consumer con = new Consumer(r);
        //生产者线程
        Thread t0 = new Thread(pro);
        Thread t1 = new Thread(pro);
        //消费者线程
        Thread t2 = new Thread(con);
        Thread t3 = new Thread(con);
        //启动线程
        t0.start();
        t1.start();
        t2.start();
        t3.start();
    }
}

/**
 * @decrition 生产者线程
 */
class Producer implements Runnable {
    private ResourceByCondition r;

    Producer (ResourceByCondition r) {
        this.r = r;
    }

    @Override
    public void run() {
        while (true) {
            r.product("something");
        }
    }
}

/**
 * @decrition 消费者线程
 */
class Consumer implements Runnable {
    private ResourceByCondition r;

    Consumer(ResourceByCondition r) {
        this.r = r;
    }

    @Override
    public void run() {
        while (true) {
            r.consume();
        }
    }
}