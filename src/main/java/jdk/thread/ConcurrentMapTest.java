package jdk.thread;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * concurrentHashmap和Hashmap比较
 * <p>
 * Created by hujianbo on 2018/1/26.
 */
public class ConcurrentMapTest {
    public static void main(final String[] arguments) {

        //Map<String, String> map = new ConcurrentHashMap<String, String>();
        //
        //map.put("1", "One");
        //map.put("2", "Two");
        //map.put("3", "Three");
        //map.put("5", "Five");
        //map.put("6", "Six");
        //
        //System.out.println("Initial ConcurrentHashMap: " + map);
        //Iterator<String> iterator = map.keySet().iterator();
        //
        //try {
        //    while (iterator.hasNext()) {
        //        String key = iterator.next();
        //        if (key.equals("3")) {
        //            map.put("4", "Four");
        //        }
        //    }
        //} catch (ConcurrentModificationException cme) {
        //    cme.printStackTrace();
        //}
        //System.out.println("ConcurrentHashMap after modification: " + map);

        Map<String, String> map = new HashMap<String, String>();

        map.put("1", "One");
        map.put("2", "Two");
        map.put("3", "Three");
        map.put("5", "Five");
        map.put("6", "Six");

        System.out.println("Initial HashMap: " + map);
        Iterator<String> iterator = map.keySet().iterator();

        try {
            while (iterator.hasNext()) {
                String key = iterator.next();
                if (key.equals("3")) {
                    map.put("4", "Four");
                }
            }
            System.out.println("HashMap after modification: " + map);
        } catch (ConcurrentModificationException cme) {
            //因为Hashmap线程不安全的，这里多线程跑报错
            //if (modCount != expectedModCount) {
            //    throw new ConcurrentModificationException();
            // }
            cme.printStackTrace();
        }
    }
}
