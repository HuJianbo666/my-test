package copy;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Hu Jianbo
 * @date: 2018/9/18
 */
public class MyHashMap<K, V> extends AbstractMap<K, V> implements Map<K, V>, Cloneable, Serializable {

    private static final long serialVersionUID = -7734449945942444742L;

    public static void main(String[] args) {
        HashMap<Object, Object> map = new HashMap<>(4 * 4 / 3);
        map.put("k1", "v1");
        map.get("k1");
        map.remove("k1");
        Set<Entry<Object, Object>> entries = map.entrySet();
        Set<Object> objects = map.keySet();
    }

    /* -----------------Fields------------------------ */

    transient Node<K,V>[] table;


    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

    final private V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {


        return null;
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    static class Node<K, V> implements Map.Entry<K, V> {

        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public final K getKey() {
            return key;
        }

        @Override
        public final V getValue() {
            return value;
        }

        @Override
        public final String toString() {
            return key + "=" + value;
        }

        @Override
        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }

            if (o instanceof Entry) {
                Entry<?, ?> e = (Entry<?, ?>) o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue())) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }
    }

}
