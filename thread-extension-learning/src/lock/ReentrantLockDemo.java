package lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static void main(String[] args) {

        // 互斥锁 可重入锁
        ReentrantLock reentrantLock = new ReentrantLock();

        for (int i = 0; i < 10; i ++) {
            new TestLock(reentrantLock).start();
        }
    }
}

class TestLock extends Thread {

    private ReentrantLock lock;

    private static int value = 1;

    public TestLock(ReentrantLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        v1();
    }

    private synchronized void v1() {

        try {
            lock.lock();

            for (int i = 0; i < 5000; i ++) {
                System.out.println("value = " + value ++);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
