package socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Demo07 {

    public static void main(String[] args) throws IOException {

        DatagramChannel dc = DatagramChannel.open();

        dc.configureBlocking(false);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        String msg = "hello udp";

        buffer.put(msg.getBytes());

        buffer.flip();

        dc.send(buffer, new InetSocketAddress("127.0.0.1", 9009));

    }
}
