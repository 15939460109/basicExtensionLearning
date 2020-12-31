package jvm;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

// 对象引用
public class Test5 {

    public static void main(String[] args) {

        // 强引用
        User user = new User();
        // 软引用
        SoftReference<User> soft = new SoftReference<>(new User());
        // 弱引用
        WeakReference<User> weak = new WeakReference<>(new User());

        System.out.println("new = " + user);
        System.out.println("soft = " + soft.get());
        System.out.println("weak = " + weak.get());

        System.out.println("垃圾回收");
        System.gc();

        System.out.println("new = " + user);
        System.out.println("soft = " + soft.get());
        System.out.println("weak = " + weak.get());

        System.out.println("内存不足");
        try {
            List list = new ArrayList();
            while (true) {
                list.add(new byte[1024 * 1024 * 1024 * 5]);
            }
        } catch (Throwable e) {
            System.out.println("new = " + user);
            System.out.println("soft = " + soft.get());
            System.out.println("weak = " + weak.get());
        }

    }
}
