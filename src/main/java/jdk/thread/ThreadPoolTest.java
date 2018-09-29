package jdk.thread;

import java.util.concurrent.*;

/**
 * 线程池
 * Created by hujianbo on 2018/io1/26.
 */
public class ThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<?> future = service.submit(new Thread1());
        Object o = future.get();
        System.out.println(o);

    }

    private static class Thread1 implements Callable<String>{

        @Override
        public String call() throws Exception {
            return "run..";
        }
    }
}
