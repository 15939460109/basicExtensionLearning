package hashmap;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) {

        // 信号量
        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < 10; i ++) {
            new MyThread(semaphore).start();
        }

    }

static class MyThread extends Thread {

    Semaphore semaphore;

    public MyThread(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            // 请求资源
            this.semaphore.acquire();

            System.out.println(Thread.currentThread().getName() + "正在支付");
            Thread.sleep(new Random().nextInt(7000) + 3000);

            // 释放资源
            this.semaphore.release();

            System.out.println(Thread.currentThread().getName() + "支付完成了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
}
