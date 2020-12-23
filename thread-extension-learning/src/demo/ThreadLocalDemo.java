package demo;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalDemo {

    public static void main(String[] args) {

        ThreadLocal tl;
        // spring 事务
        // mybatis pagehelper

        // set get

        // count++  A 5000  B 5000  C 5000----15000
        // v++      A 5000  B 5000  C 5000----5000 5000 5000

//        TestCount testCount = new TestCount();
//
//        new Thread(testCount, "A").start();
//        new Thread(testCount, "B").start();
//        new Thread(testCount, "C").start();

        // ===================================================================
        ThreadLocal<Integer> t1 = new ThreadLocal<>();
        ThreadLocal<Integer> t2 = new ThreadLocal<>();
        ThreadLocal<Integer> t3 = new ThreadLocal<>();
        ThreadLocal<Integer> t4 = new ThreadLocal<>();

        t1.set(1);
        t2.set(2);
        t3.set(3);
        t4.set(4);

        System.out.println(Thread.currentThread().getName() + " " + t1.get());
        System.out.println(Thread.currentThread().getName() + " " + t2.get());
        System.out.println(Thread.currentThread().getName() + " " + t3.get());
        System.out.println(Thread.currentThread().getName() + " " + t4.get());
    }

    static class TestCount extends Thread {

        private AtomicInteger count = new AtomicInteger(0);
        int v = 0;

        @Override
        public void run() {
            // v1();
            v2();
        }

        private void v1() {
            for (int i = 0; i < 5000; i ++) {
                System.out.println(Thread.currentThread().getName() + " , count = " + count.incrementAndGet());
            }
        }

        private void v2() {
            ThreadLocal<Integer> tl = new ThreadLocal<>();
            tl.set(v);

            for (int i = 0; i < 5000; i ++) {
                int v = tl.get();
                System.out.println(Thread.currentThread().getName() + " , v = " + v);
                tl.set(v + 1);
            }
        }
    }
}
