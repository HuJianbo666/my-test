package notes;

import com.google.common.base.Charsets;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Hu Jianbo
 * @date: 2018/9/21
 */
public class IoTest {

    public static void main(String[] args) throws IOException {
        String inFile = "D:\\projects\\my\\my-test\\src\\main\\java\\README.md";
        String outFile = "D:\\projects\\my\\my-test\\src\\main\\java\\README-OUT.md";

        //        // InputStream
        //        FileInputStream fis = new FileInputStream(inFile);
        //        int data = fis.read();
        //        while (data != -1) {
        //            data = fis.read();
        //        }
        //
        //        FileOutputStream fos = new FileOutputStream(outFile);
        //        fos.write("hello io".getBytes(Charsets.UTF_8));
        //
        //        InputStream bufferedInputStream = new BufferedInputStream(fis);
        //        int read = bufferedInputStream.read();
        //
        //        RandomAccessFile randomAccessFile = new RandomAccessFile(inFile, "r");
        //        long length = randomAccessFile.length();
        //        long nextend = length - 1;
        //
        //        while (nextend > 0) {
        //            randomAccessFile.seek(nextend);
        //            String s = new String(randomAccessFile.readLine().getBytes("ISO-8859-1"), Charsets.UTF_8);
        //            System.out.println(s);
        //            nextend--;
        //        }

        //        final PipedOutputStream out = new PipedOutputStream();
        //        final PipedInputStream in = new PipedInputStream(out);
        //
        //        Thread thread1 = new Thread(() -> {
        //            try {
        //                out.write("pip in".getBytes());
        //                out.close();
        //            } catch (IOException e) {
        //                e.printStackTrace();
        //            }
        //        });
        //
        //        Thread thread2 = new Thread(() -> {
        //            try {
        //                int read = in.read();
        //                while (read != -1) {
        //                    System.out.print((char) read);
        //                    read = in.read();
        //                }
        //                in.close();
        //            } catch (IOException e) {
        //                e.printStackTrace();
        //            }
        //        });
        //
        //        thread1.start();
        //        thread2.start();

        ByteOutputStream out = new ByteOutputStream();
        out.write("Out put byte arrays".getBytes(Charsets.UTF_8));
        byte[] bytes = out.getBytes();
        System.out.println(bytes);
        System.out.println(new String(bytes));
    }
}
