package guava;

import com.google.common.base.Optional;

/**
 * 处理null
 * Created by hujianbo on 2018/1/25.
 */
public class OptionalTest {

    public static void main(String[] args) {
        Integer v1 = null;
        Integer v2 = 10;

        Optional<Integer> o1 = Optional.fromNullable(v1);
        Optional<Integer> o2 = Optional.fromNullable(v2);
        Integer sum = sum(o1, o2);
        System.out.println(sum);
    }

    private static Integer sum(Optional<Integer> o1, Optional<Integer> o2) {
        System.out.println(o1.isPresent());
        System.out.println(o2.isPresent());
        //如果为null，返回指定的值
        Integer or = o1.or(1);
        //返回实例
        Integer get = o2.get();

        return or + get;
    }
}
