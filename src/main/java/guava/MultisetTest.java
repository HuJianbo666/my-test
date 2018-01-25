package guava;

import com.google.common.collect.LinkedHashMultiset;
import com.google.common.collect.Multiset;

/**
 * 新型集合类
 * Created by hujianbo on 2018/1/25.
 */
public class MultisetTest {
    public static void main(String[] args) {
        Multiset<String> set = LinkedHashMultiset.create();
        set.add("a");
        set.add("a");
        set.add("a");
        set.add("a");
        //添加或删除指定元素使其在集合中的数量是count
        set.setCount("a", 5);
        set.stream().forEach(p -> System.out.println(p));

        set.clear(); //清空集合
        System.out.println(set);
    }
}
