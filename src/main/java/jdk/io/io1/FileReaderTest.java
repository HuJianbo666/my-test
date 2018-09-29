package jdk.io.io1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * @author Hu Jianbo
 * @date: 2018/9/6
 */
public class FileReaderTest {

    public static void main(String[] args) throws FileNotFoundException {
        Reader reader = new FileReader(new File("D:\\projects\\my\\my-test\\src\\main\\java\\jdk\\io\\io1\\iodemo.txt"));
        try {
            int data = reader.read();
            while (data != -1) {
                System.out.println((char) data);
                data = reader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
