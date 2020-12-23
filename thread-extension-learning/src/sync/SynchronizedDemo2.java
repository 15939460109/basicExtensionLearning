package sync;

public class SynchronizedDemo2 {

    public static void main(String[] args) {

        Object obj = new Object();

        for (int i = 0; i < 10; i ++) {
            new TestSync2(obj).start();
        }
    }
}

class TestSync2 extends Thread {

    private Object obj;
    private static int value = 1;

    public TestSync2(Object obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        v1();
    }

    private void v1() {

        synchronized(obj) {
            for (int i = 0; i < 5000; i ++) {
                System.out.println("value = " + value ++);
            }
        }
    }
}
