package Phaser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Phaser;

public class PhaserDemo2 {

    public static void main(String[] args) throws IOException {
        Phaser phaser = new Phaser(6);

        for (int i = 0; i < 5; i ++) {
            new Thread(new Task(phaser)).start();
        }

        System.out.println("input enter");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        reader.readLine();

        // 到达并退出
        phaser.arriveAndDeregister();

        // 注册+1
        phaser.bulkRegister(1);
    }

    static class Task implements Runnable {

        Phaser phaser;

        public Task(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {

            for (int i = 0; i < 3; i ++) {
                phaser.arriveAndAwaitAdvance();

                System.out.println(Thread.currentThread().getName());
            }
        }
    }
}
