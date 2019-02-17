package guava;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RateLimterTest {
    public static void main(String[] args) {
        // 1秒执行0.5个（即2秒执行1个）
        RateLimiter rateLimiter = RateLimiter.create(0.5);
        List<Runnable> tasks = Lists.newArrayList();
        for(int i = 0; i < 10; i++) {
            tasks.add(new Run(i));
        }
        ExecutorService threadPool  = Executors.newCachedThreadPool();
//        for (Runnable task : tasks) {
//            //rateLimiter.acquire()会阻塞线程
//            System.out.println("等待时间：" + rateLimiter.acquire());
//            threadPool.execute(task);
//        }

        for (Runnable task : tasks) {
            //rateLimiter.acquire()会阻塞线程
            if (!rateLimiter.tryAcquire(4, TimeUnit.SECONDS)) {
                System.out.println("未抢到令牌：" + rateLimiter.acquire());
            } else {
                System.out.println("等待时间：" + rateLimiter.acquire());
                threadPool.execute(task);
            }
        }
    }

    private static class Run implements Runnable{
        private Integer i;

        public Run(Integer i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println("i:" + i);
        }
    }
}
