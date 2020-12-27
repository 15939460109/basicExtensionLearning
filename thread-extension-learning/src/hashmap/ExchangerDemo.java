package hashmap;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread1(exchanger).start();
        new Thread2(exchanger).start();
    }

    static class Thread1 extends Thread{

        Exchanger<String> exchanger;

        public Thread1(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            System.out.println("Thread1 run");

            try {
                String str = exchanger.exchange("Thread1 --> Thread2数据");
                System.out.println("Thread1:" + str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Thread1 finish");
        }
    }

    static class Thread2 extends Thread{

        Exchanger<String> exchanger;

        public Thread2(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            System.out.println("Thread2 run");

            try {
                String str = exchanger.exchange("Thread2 --> Thread1数据");
                System.out.println("Thread2:" + str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Thread2 finish");
        }
    }
}
