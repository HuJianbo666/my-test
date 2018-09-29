package jdk.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * 写文件demo
 * <p>
 * Created by hujianbo on 2018/io1/26.
 */
public class FileWriteTest {
    public static void main(String[] args) {
        try {
            RandomAccessFile fromFile = new RandomAccessFile("src/main/java/jdk/nio/readme.md", "rw");
            FileChannel fromChannel = fromFile.getChannel();

            RandomAccessFile toFile = new RandomAccessFile("src/main/java/jdk/nio/toFile.md", "rw");
            FileChannel toChannel = toFile.getChannel();

            long position = 0;
            long count = fromChannel.size();
            fromChannel.transferTo(position, count, toChannel);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
