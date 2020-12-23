package lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo2 {

    public static void main(String[] args) {

        // 互斥锁 可重入锁
        ReentrantLock reentrantLock = new ReentrantLock();

        for (int i = 0; i < 10; i ++) {
            new TestLock2(reentrantLock).start();
        }

        // System.out.println(reentrantLock.tryLock());
    }
}

class TestLock2 extends Thread {

    private ReentrantLock lock;

    private int value = 1;

    public TestLock2(ReentrantLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            v1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 重入
    private void test() {
        lock.lock();
        System.out.println("=======================test");
        lock.unlock();
    }

    private synchronized void v1() throws InterruptedException {

        if (lock.tryLock(1, TimeUnit.SECONDS)) { // 判断是否拿到了锁
            try {
                for (int i = 0; i < 100; i ++) {
                    test();
                    System.out.println("value = " + value ++);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
