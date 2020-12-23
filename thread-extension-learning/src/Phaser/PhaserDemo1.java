package Phaser;

import java.util.Random;
import java.util.concurrent.Phaser;

public class PhaserDemo1 {

    public static void main(String[] args) {

        Phaser phaser = new Phaser();

        for (int i = 0; i < 10; i ++) {
            phaser.register();
            new Thread(new Task(phaser)).start();
        }

    }

    static class Task implements Runnable {
        Phaser phaser;

        public Task(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(new Random().nextInt(5000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Advance 当最后一个线程达到10的值
                // 线程同时唤醒并运行
                // 触发onAdvance方法
                int val = phaser.arriveAndAwaitAdvance();

                System.out.println(Thread.currentThread().getName() + " = " + val);
            }
        }
    }
}
