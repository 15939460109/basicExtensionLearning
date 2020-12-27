package hashmap;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("执行结束，开始下一轮");
            }
        });

        // A 1s
        // B 10s
        // C 8s

        for (int i = 0; i < 3; i ++) {
            new MyThread(cyclicBarrier).start();
        }
    }

    static class MyThread extends Thread {
        CyclicBarrier cyclicBarrier;

        public MyThread(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println(Thread.currentThread().getName() + "开始执行");
                try {
                    Thread.sleep(new Random().nextInt(10000));
                    this.cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
