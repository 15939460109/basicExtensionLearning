package demo;

import java.nio.*;

public class BufferDemo {

    // 能读、能写
    ByteBuffer byteBuffer;
    CharBuffer charBuffer;
    ShortBuffer shortBuffer;
    IntBuffer intBuffer;
    LongBuffer longBuffer;
    FloatBuffer floatBuffer;
    DoubleBuffer doubleBuffer;

    public static void main(String[] args) {

        String data = "hello nio";

        // 创建
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 写数据
        byteBuffer.put(data.getBytes());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.capacity());
        // limit 1024  写操作的最大限制
        // position 9  当前写入数据的位置
        // capacity 1024  能写入的最大容量

        System.out.println("----------------------------------------");

        // 切换模式---读操作
        byteBuffer.flip();
        int limit = byteBuffer.limit();
        System.out.println(limit);
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.capacity());
        // limit 9  能读的最大限制
        // position 0  当前读数据的位置
        // capacity 1024  能读的最大容量

        byte[] bytes = new byte[limit];
        // 传一个参数，数组多大，就能读多少
        // byteBuffer.get(bytes);
        // 读数据，放入数组中，offset是放入数组的起始位置，length是放入的长度
        byteBuffer.get(bytes, 0, 3);
        System.out.println("[0-3] = " + new String(bytes));

        System.out.println("----------------------------------------");

        System.out.println(limit);
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.capacity());

        // 还原数组
        bytes = new byte[limit];

        // 对当前位置做一个标记 position = 3
        byteBuffer.mark();
        byteBuffer.get(bytes, 0, 2);
        System.out.println("[3-2] = " + new String(bytes));

        System.out.println("----------------------------------------");

        System.out.println(limit);
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.capacity());

        // 还原标记，position位置还原到标记位置 3
        byteBuffer.reset();

        System.out.println("----------------------------------------");

        System.out.println(limit);
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.capacity());

        // 剩余数据 limit - position
        System.out.println("remaining = " + byteBuffer.remaining());

        System.out.println("----------------------------------------");

        // 还原到0
        byteBuffer.rewind();
        System.out.println(byteBuffer.position());
    }
}
