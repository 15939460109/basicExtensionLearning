package lock;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

    public static void main(String[] args) {
        TestRW testRW = new TestRW();

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        // read
        for(int i = 0; i < 10; i ++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        testRW.read();
                    }
                }
            });
        }

        // write
        for(int i = 0; i < 10; i ++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        testRW.write();
                    }
                }
            });
        }
    }
}

class TestRW {

    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    HashMap<String, Integer> map = new HashMap<>();

    public void read() {
        try {
            readWriteLock.readLock().lock();
            Integer data = map.get("data");
            System.out.println(Thread.currentThread().getName() + "读 = " + data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void write() {
        try {
            readWriteLock.writeLock().lock();
            int data = new Random().nextInt(1000);
            map.put("data", data);
            System.out.println(Thread.currentThread().getName() + "写入操作 = " + data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
