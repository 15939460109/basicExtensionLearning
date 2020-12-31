package jvm;

import java.util.Vector;

// 堆内存
public class Test3 {

    public static void main(String[] args) {

        // Xms  初始堆大小
        // Xmx  最大堆大小
        // -XX:NewSize  年轻代大小
        // -XX:NewRatio  年轻代和老年代比值
        // -XX:SurvivorRatio  eden区和survivor区比值

        System.out.println(Runtime.getRuntime().maxMemory() / 1024 / 1024 + "M");

        Vector v = new Vector();
        for (int i = 0; i < 10; i ++) {
            byte[] b = new byte[1024 * 1024];
            v.add(b);
            System.out.println((i + 1) + "M 分配空间");
        }
    }
}
