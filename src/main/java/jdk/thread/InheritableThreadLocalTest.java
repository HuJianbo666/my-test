package jdk.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * InheritableThreadLocal, 子线程获取主线程本地数据
 *
 * @author bb
 */
public class InheritableThreadLocalTest {

    public static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
//    private static ThreadPoolExecutor pool = ThreadPoolFactory.getDefaultThreadPool();
    private static ExecutorService pool = Executors.newFixedThreadPool(1);

    public static void main(String[] args) throws InterruptedException {

        System.out.println("主线程开始");

        inheritableThreadLocal.set("主线程1");
        pool.submit(() -> {
            System.out.println("子线程开启");
            System.out.println("子线程后去本地变量值： " + Thread.currentThread().getName() + "::" + inheritableThreadLocal.get());
            System.out.println("子线程结束");
        });

        Thread.sleep(1000);

        inheritableThreadLocal.set("主线程2");
        pool.submit(() -> {
            System.out.println("子线程开启");
            System.out.println("子线程后去本地变量值： " + Thread.currentThread().getName() + "::" + inheritableThreadLocal.get());
            System.out.println("子线程结束");
        });

        System.out.println("主线程结束");

        pool.shutdown();
    }
}
