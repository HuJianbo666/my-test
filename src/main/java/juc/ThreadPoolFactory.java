package juc;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author HuJianbo
 */
public class ThreadPoolFactory {
    private static volatile ThreadPoolExecutor pool;

    public static ThreadPoolExecutor getDefaultThreadPool() {
        if (pool == null) {
            synchronized (ThreadPoolExecutor.class) {
                if (pool == null) {
                    pool = new ThreadPoolExecutor(5,
                            6,
                            10L,
                            TimeUnit.SECONDS,
                            new LinkedBlockingQueue<>(),
                            new ThreadFactory() {
                                final AtomicInteger threadNumber = new AtomicInteger(1);
                                final String namePrefix = "self-defined-";

                                @Override
                                public Thread newThread(Runnable r) {
                                    Thread t = new Thread(r, namePrefix + threadNumber.getAndIncrement());
                                    if (t.isDaemon()) {
                                        t.setDaemon(true);
                                    }
                                    if (t.getPriority() != Thread.NORM_PRIORITY) {
                                        t.setPriority(Thread.NORM_PRIORITY);
                                    }
                                    return t;
                                }
                            });
                }
            }

        }
        return pool;
    }
}
