package guava;

import com.google.common.base.Joiner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Joiner 提供了各种方法来处理字符串加入操作，对象等。
 * Created by hujianbo on 2018/1/25.
 */
public class JoinerTest {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Joiner.on(",").skipNulls().appendTo(sb, "Hello", "guava", "rest1", "rest2");
        System.out.println(sb);
        System.out.println(Joiner.on(",").useForNull("none").join(1, null, 3));
        System.out.println(Joiner.on(",").appendTo(sb, "", 1, 2));
        System.out.println(Joiner.on(",").join(Arrays.asList(1, 2, 3, 4, 5)));

        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        System.out.println(Joiner.on(",").withKeyValueSeparator("=").join(map));
    }
}
