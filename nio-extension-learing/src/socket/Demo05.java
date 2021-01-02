package socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Demo05 {


    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9001));

        socketChannel.configureBlocking(false);

        String msg = "hello nio";
        ByteBuffer byteBuffer = ByteBuffer.allocate(msg.getBytes().length);

        byteBuffer.put(msg.getBytes());

        byteBuffer.flip();

        socketChannel.write(byteBuffer);

        socketChannel.close();

    }
}
