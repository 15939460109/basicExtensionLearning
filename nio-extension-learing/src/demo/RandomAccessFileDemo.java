package demo;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {

    public static void main(String[] args) throws IOException {

        RandomAccessFile raf = new RandomAccessFile("D:/test.txt", "rw");

        // 写操作
//        raf.write(1);
//        raf.write(2);
//        raf.write(3);

        // 读操作
//        int v1 = raf.read();
//        int v2 = raf.read();
//        int v3 = raf.read();
//        System.out.println(v1);
//        System.out.println(v2);
//        System.out.println(v3);

        // 获取文件字符数
        System.out.println(raf.length());
        // 获取当前指针位置
        System.out.println(raf.getFilePointer());

        // 设置当前指针位置
        raf.seek(1);
//        int v = raf.read();
//        System.out.println(v);

//        raf.write(7);
//        raf.write(8);
//        raf.write(9);

        // 按照指针位置写操作时会重写之后的数据
        int v1 = raf.read();
        int v2 = raf.read();
        int v3 = raf.read();
        int v4 = raf.read();
        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v3);
        System.out.println(v4);
    }
}
