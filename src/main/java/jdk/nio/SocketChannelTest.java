package jdk.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by hujianbo on 2018/io1/26.
 */
public class SocketChannelTest {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("http://www.ihboo.com", 80));
            //socketChannel.connect(new InetSocketAddress(8020));
            ByteBuffer buf = ByteBuffer.allocate(1024);
            int read = socketChannel.read(buf);
            while (read != -1) {
                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.println(read);
                }
                buf.clear();
                read = socketChannel.read(buf);
            }

            socketChannel.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
