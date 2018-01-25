package guava;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

/**
 * 不可变集合
 * Created by hujianbo on 2018/1/25.
 */
public class CollectionTest {
    public static void main(String[] args) {
        ImmutableSet<String> set = ImmutableSet.of("a", "b", "c");
        ImmutableSet<String> set1 = ImmutableSet.copyOf(set);
        ImmutableSet<String> set2 = ImmutableSet.<String>builder().addAll(set).add("e").build();
        System.out.println(set2);
        ImmutableList<String> list = set.asList();
    }
}
