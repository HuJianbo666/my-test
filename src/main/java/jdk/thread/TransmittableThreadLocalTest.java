package jdk.thread;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



/**
 * 处理InheritableThreadLocal获取主线程数据不能重新赋值的问题
 *
 * @author HuJianbo
 */
public class TransmittableThreadLocalTest {

    private static final ExecutorService service = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
        TransmittableThreadLocal<String> parent = new TransmittableThreadLocal<>();

        parent.set("主线程1：value");

        Runnable ttlRunnable = TtlRunnable.get(() -> System.out.println("running..."));
        service.submit(ttlRunnable);

        String value = parent.get();
        System.out.println("get value in parent: " + value);


        parent.set("主线程2：value");

        String value2 = parent.get();
        System.out.println("get value in parent: " + value2);

        service.shutdown();
    }
}
