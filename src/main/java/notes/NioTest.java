package notes;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author BG340927
 * @date 2018/10/9
 */
public class NioTest {

    @Test
    public void Channel() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("D:\\projects\\my\\my-test\\src\\main\\java\\README.md", "rw");

        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf);

        while (bytesRead != -1) {
            System.out.println("Read: " + bytesRead);
            // 写模式切换到读模式
            buf.flip();

            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }



}
