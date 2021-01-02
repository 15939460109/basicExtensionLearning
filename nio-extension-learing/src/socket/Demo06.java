package socket;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class Demo06 {

    public static void main(String[] args) throws Exception {

        int port = 9009;
        Selector selector = Selector.open();
        DatagramChannel dc = DatagramChannel.open();
        dc.configureBlocking(false);
        dc.bind(new InetSocketAddress(port));

        dc.register(selector, SelectionKey.OP_READ);
        while (true) {

            selector.select();

            Set<SelectionKey> keys = selector.selectedKeys();

            Iterator<SelectionKey> it = keys.iterator();

            while (it.hasNext()) {
                SelectionKey key = it.next();

                if (key.isReadable()) {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    dc.receive(buffer);

                    buffer.flip();

                    System.out.println(new String(buffer.array(), 0, buffer.limit()));

                }

                it.remove();
            }
        }

    }
}
