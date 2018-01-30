package jdk.jdk8;

import java.util.Optional;

/**
 * 1. of() 创建Optional对象，传null报空指针
 * 2. ofNullable() 与of()区别就是可以传null，返回empty Optional对象
 * 3. isPresent() 检查Optional实例是否包含值
 * 4. get() 获取值，没有值抛出NoSuchElementException
 * 5. ifPresent(Consumer<? super T> consumer),对Optional值作处理
 * 6. orElse()有值返回，没值返回指定的值
 * 7. orElseGet()与orElse类似，可以接受Supplier接口实现生成默认值
 * 8. map(Function<? super T, ? extends U> mapper)对Optional实例的值执行一系列操作
 * 9. flatMap与map类似，区别在于flatmap必须返回值是Optioanal实例，map会自动封装
 * 10. filter()过滤条件
 * <p>
 * Created by hujianbo on 2018/1/30.
 */
public class OptionalTest {

    public static void main(String[] args) {
        Optional<String> o1 = Optional.of("oo");

        Optional<Object> o2 = Optional.ofNullable(null);

        boolean present = o1.isPresent();

        String s = o1.get();

        o1.ifPresent(value -> System.out.println(value));

        Object o = o2.orElse("no value");

        Object o3 = o2.orElseGet(() -> "default value");

        //Object o4 = o2.orElseThrow(() -> RuntimeException::new);

        Optional<String> s1 = o1.map((value) -> value.toUpperCase());

        Optional<String> s2 = o1.flatMap((value) -> Optional.of(value.toUpperCase()));

        o1.filter(v -> v != null).filter(v -> v != "22");


    }
}
