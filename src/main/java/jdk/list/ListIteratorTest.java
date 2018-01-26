package jdk.list;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * listIterator可以对list迭代中进行修改添加删除操作
 * <p>
 * Created by hujianbo on 2018/1/26.
 */
public class ListIteratorTest {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        ListIterator listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            Integer next = (Integer) listIterator.next();
            if (next == 2) {
                //listIterator.add(22);
                listIterator.set(222);
                listIterator.remove();
            }
            System.out.println(next);
        }
        System.out.println("----");
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }
        System.out.println("--------");
        list.forEach(p -> System.out.println(p));

    }
}
