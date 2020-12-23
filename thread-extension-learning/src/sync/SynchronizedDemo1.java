package sync;

public class SynchronizedDemo1 {

    public static void main(String[] args) {

        // synchronized 同步方法锁定的是this，但是这里new了10个对象，用的不是同一把锁
        // 所以需要将方法变成static修饰的，这样方法锁定的就是class
        for (int i = 0; i < 10; i ++) {
            new TestSync().start();
        }
    }
}

class TestSync extends Thread {

    private static int value = 1;

    @Override
    public void run() {
        v1();
    }

    private static synchronized void v1() {

        for (int i = 0; i < 5000; i ++) {
            System.out.println("value = " + value ++);
        }
    }

}
