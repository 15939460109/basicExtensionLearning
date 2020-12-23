package lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo3 {

    public static void main(String[] args) throws InterruptedException {

        // 互斥锁 可重入锁
        ReentrantLock reentrantLock = new ReentrantLock();

        Thread thread = new TestLock3(reentrantLock);
        thread.start();

        Thread.sleep(4000);
        thread.interrupt(); // 中断

         System.out.println(reentrantLock.tryLock(5, TimeUnit.SECONDS));
    }
}

class TestLock3 extends Thread {

    private ReentrantLock lock;

    private int value = 1;

    public TestLock3(ReentrantLock lock) {
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
    private void test() throws InterruptedException {
        lock.lock();
        Thread.sleep(1000);
        System.out.println("=======================test");
        lock.unlock();
    }

    private synchronized void v1() throws InterruptedException {

        try {
            lock.lockInterruptibly();

            for (int i = 0; i < 100; i ++) {
                // test();
                System.out.println("value = " + value ++);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
