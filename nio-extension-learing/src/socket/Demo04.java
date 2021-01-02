package socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Demo04 {


    public static void main(String[] args) throws IOException {

        int port = 9001;
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        ssc.socket().bind(new InetSocketAddress(port));


        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {

            selector.select();

            Set<SelectionKey> keys = selector.selectedKeys();

            Iterator<SelectionKey> it = keys.iterator();

            while (it.hasNext()) {
                SelectionKey key = it.next();

                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();

                    sc.configureBlocking(false);

                    sc.register(selector, SelectionKey.OP_READ);

                } else if (key.isReadable()) {

                    SocketChannel channel = (SocketChannel) key.channel();

                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                    int len = channel.read(byteBuffer);

                    if (len > 0) {
                        byteBuffer.flip();

                        System.out.println(new String(byteBuffer.array(), 0, byteBuffer.limit()));
                    }
                }
                it.remove();
            }
        }

        // 服务器监听端口
        // 通道
        // 非阻塞
        // selector
        // 设置监听
        // accept 继续监听 read
    }
}
