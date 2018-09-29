package guava;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.Map;

/**
 * Multimap一个key可以对应多个值
 * Created by hujianbo on 2018/io1/25.
 */
public class MultimapTest {
    public static void main(String[] args) {
        Multimap<String, Object> map = HashMultimap.create();
        map.put("a",1);
        map.put("a",2);
        map.put("a",3);
        Collection<Object> a = map.get("a");
        Map<String,Collection<Object>> mapView=map.asMap();

        System.out.println(mapView);
        System.out.println(map);
    }
}
