package juc.tools;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author HuJianbo
 */
public class ExecutorsTest {
    public static void main(String[] args) throws IOException, InterruptedException {
//        // core max都是1
//        ExecutorService service1 = Executors.newSingleThreadExecutor();
//        // core max都是自己输入的
//        ExecutorService service2 = Executors.newFixedThreadPool(4);
//        // 最大线程数为int最大值
//        ExecutorService service3 = Executors.newCachedThreadPool();
//        // max为int最大值，可延迟执行
//        ExecutorService service4 = Executors.newScheduledThreadPool(4);
//        ((ScheduledExecutorService) service4).schedule(() -> {}, 3, TimeUnit.SECONDS);
        ExecutorService service5 = Executors.newWorkStealingPool(4);
        System.out.println(Runtime.getRuntime().availableProcessors());
        service5.execute(new R(1000));
        service5.execute(new R(2000));
        service5.execute(new R(3000));
        service5.execute(new R(1000));
        service5.execute(new R(2000));

        // WorkStealing是精灵线程(守护线程、后台线程)，主线程不阻塞，看不到输出。
        // 虚拟机不停止，守护线程不停止
        System.out.println("主线程");
        TimeUnit.SECONDS.sleep(10);
    }

    private static class R implements Runnable {
        int time;

        public R(int time) {
            this.time = time;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(time + ":" + Thread.currentThread().getName());
        }
    }
}
