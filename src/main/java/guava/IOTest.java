package guava;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 读写文件
 * Created by hujianbo on 2018/io1/25.
 */
public class IOTest {

    private void writeFile(String content, File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        Files.write(content.getBytes(Charsets.UTF_8), file);
    }

    private List<String> readFile(File file) throws IOException {
        if (!file.exists()) {
            return ImmutableList.of();
        }
        return Files.readLines(file, Charsets.UTF_8);
    }

    private void copyFile(File from, File to) throws IOException {
        if (!from.exists()) {
            return;
        }
        if (!to.exists()) {
            to.createNewFile();
        }
        Files.copy(from, to);
    }
}
