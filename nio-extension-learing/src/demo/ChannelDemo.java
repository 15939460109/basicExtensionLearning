package demo;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ChannelDemo {

    public static void main(String[] args) throws IOException {

        FileChannel fileChannel;

        ServerSocketChannel serverSocketChannel;
        SocketChannel socketChannel;

        DatagramChannel datagramChannel;

        // 文件拷贝
        // 直接内存拷贝
        // 通道传输
        // 分散和聚集
        // 截断
        // 加锁

//        t1();
        t5();
    }

    public static void t6() throws IOException {
        FileLock fl;
    }

    public static void t5() throws IOException {
        FileOutputStream fos = new FileOutputStream("D:/t1sss.txt", true);
        FileChannel outChannel = fos.getChannel();
        outChannel.truncate(7);
    }

    public static void t4() throws IOException {
        FileInputStream fis = new FileInputStream("D:/t1.txt");
        FileChannel inChannel = fis.getChannel();

        ByteBuffer header = ByteBuffer.allocate(5);
        ByteBuffer body = ByteBuffer.allocate(10);

        ByteBuffer[] arr = {header, body};

        inChannel.read(arr);

        for (ByteBuffer b : arr) {
            // 切换模式
            b.flip();

            System.out.println(new String(b.array(), 0, b.limit()));
        }

        FileOutputStream fos = new FileOutputStream("D:/t1sss.txt");
        FileChannel outChannel = fos.getChannel();
        outChannel.write(arr);

        fis.close();
        fos.close();
        inChannel.close();
        outChannel.close();
    }

    // 通道传输
    public static void t3() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("D:/t1.txt"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("D:/t1ss.txt"),
                StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        long start = System.currentTimeMillis();
        inChannel.transferTo(0, inChannel.size(), outChannel);
        long end = System.currentTimeMillis();

        System.out.println(end - start);

        inChannel.close();
        outChannel.close();
    }

    // 直接内存拷贝
    public static void t2() throws IOException {
        FileInputStream fis = new FileInputStream("D:/t1.txt");
        FileChannel inChannel = fis.getChannel();

        // 内存映射
        MappedByteBuffer mappedByteBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());

        long length = inChannel.size();
        byte[] bytes = new byte[(int) length];

        long start = System.currentTimeMillis();
        mappedByteBuffer.get(bytes);
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    // 文件拷贝
    public static void t1() throws IOException {
        FileInputStream fis = new FileInputStream("D:/t1.txt");
        FileOutputStream fos = new FileOutputStream("D:/t1s.txt");

        FileChannel inputChannel = fis.getChannel();
        FileChannel outputChannel = fos.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (inputChannel.read(byteBuffer) != -1) {
            // 切换读模式
            byteBuffer.flip();
            outputChannel.write(byteBuffer);

            byteBuffer.clear();
        }

        fis.close();
        fos.close();
        inputChannel.close();
        outputChannel.close();
    }

}
