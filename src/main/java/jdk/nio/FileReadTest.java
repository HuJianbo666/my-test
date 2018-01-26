package jdk.nio;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * 读文件demo
 * <p>
 * Created by hujianbo on 2018/1/26.
 */
public class FileReadTest {
    public static void main(String[] args) {
        try {

            Charset charset = Charset.forName("UTF-8");
            //Java.nio.charset.Charset处理了字符转换问题。它通过构造CharsetEncoder和CharsetDecoder将字符序列转换成字节和逆转换。
            CharsetDecoder decoder = charset.newDecoder();

            RandomAccessFile aFile = new RandomAccessFile("src/main/java/jdk/nio/readme.md", "rw");
            FileChannel inChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            CharBuffer cb = CharBuffer.allocate(1024);

            int byteRead = inChannel.read(buf);
            while (byteRead != -1) {
                System.out.println("read" + byteRead);
                buf.flip();
                decoder.decode(buf, cb, false);
                cb.flip();
                while (cb.hasRemaining()) {
                    System.out.print(cb.get());
                }

                buf.clear();
                cb.clear();
                byteRead = inChannel.read(buf);
            }
            aFile.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
