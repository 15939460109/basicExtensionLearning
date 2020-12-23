package Phaser;

import java.util.concurrent.Phaser;

public class PhaserDemo3 {

    public static void main(String[] args) {
        MyPhaser phaser = new MyPhaser();

        for (int i = 0; i < 3; i ++) {
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

            while (!phaser.isTerminated()) {
                phaser.arriveAndAwaitAdvance();

                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    static class MyPhaser extends Phaser {

        @Override
        protected boolean onAdvance(int phase, int registeredParties) {

            System.out.println("---------------------phase=" + phase +
                    "---------------registeredParties=" + registeredParties);

            // true退出
            // false继续执行新的一轮
            return phase + 1 >= 5 || registeredParties == 0;
        }
    }
}
