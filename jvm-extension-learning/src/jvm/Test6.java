package jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

// 虚引用
public class Test6 {

    public static void main(String[] args) {
        // 引用队列
        ReferenceQueue<User> queue = new ReferenceQueue<>();

        // 虚引用
        PhantomReference<User> phantomReference = new PhantomReference<>(new User(), queue);

        List list = new ArrayList();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    list.add(new byte[1024 * 1024 * 1024]);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 获取不到
                    System.out.println(phantomReference.get());
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Reference<? extends User> reference = queue.poll();
                    if (reference != null) {
                        System.out.println("虚引用对象被回收了 = " + reference);
                        System.out.println("回收对象 = " + reference.get());
                    }
                }
            }
        }).start();
    }

}
