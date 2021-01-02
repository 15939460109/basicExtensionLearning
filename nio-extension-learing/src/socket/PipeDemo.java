package socket;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class PipeDemo {

    public static void main(String[] args) throws Exception {

        Pipe pipe = Pipe.open();
        // sink
        // sourse

        Thread s = new Thread(new Sender(pipe));
        Thread r = new Thread(new Recv(pipe));

        s.start();
        r.start();
    }

    static class Sender implements Runnable {
        Pipe.SinkChannel sinkChannel;

        public Sender(Pipe pipe) {
            sinkChannel = pipe.sink();
        }

        @Override
        public void run() {
            try {
                ByteBuffer buf = ByteBuffer.allocate(1024);
                buf.put("hello pipe".getBytes());
                buf.flip();
                sinkChannel.write(buf);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class Recv implements Runnable {

        Pipe.SourceChannel sourceChannel;

        public Recv(Pipe pipe) {
            sourceChannel = pipe.source();
        }

        @Override
        public void run() {
            try {

                ByteBuffer buff = ByteBuffer.allocate(1024);
                int len = sourceChannel.read(buff);
                System.out.println(new String(buff.array(), 0, len));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
