package notes;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author BG340927
 * @date 2018/9/29
 */
public class Test1 {

    @Test
    public void test1() {
        List<String> strings = Lists.newArrayList("a", "b", "c", "d");
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == "b") {
                iterator.remove();
            }
        }
        System.out.println(strings);

        ListIterator<String> stringListIterator = strings.listIterator();
        while (stringListIterator.hasNext()) {
            if (stringListIterator.next() == "a") {
                stringListIterator.remove();
            }
        }
        System.out.println(strings);
    }
}
