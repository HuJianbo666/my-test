package jdk.io.io1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author Hu Jianbo
 * @date: 2018/9/6
 */
public class FileWriterTest {

    public static void main(String[] args) {
        try {
            Writer writer = new FileWriter("D:\\projects\\my\\my-test\\src\\main\\java\\jdk\\io\\io1\\iodemo.txt");
            writer.write("w七");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
