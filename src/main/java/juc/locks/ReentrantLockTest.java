package juc.locks;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author HuJianbo
 */
public class ReentrantLockTest {
    public static void main(String[] args) throws InterruptedException {
        BoundedBuffer boundedBuffer = new BoundedBuffer();
        ThreadFactory threadFactory = new ThreadFactory() {
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
        };
        RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                2,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                threadFactory,
                rejectedExecutionHandler);

        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    boundedBuffer.put(i);
                    System.out.println(Thread.currentThread().getName() + "-put:" + i);
                }
            }
        });
        Thread.sleep(2000);
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "-take:" + boundedBuffer.take());
                    ;
                }
            }
        });
    }
}

class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    final Condition write = lock.newCondition();
    final Condition read = lock.newCondition();

    final Object[] pools = new Object[100];

    int putIdx, takeIdx, count = 0;

    public void put(Object obj) {
        if (lock.tryLock()) {
            try {
                while (pools.length == count) {
                    write.await();
                }
                pools[putIdx] = obj;
                if (++putIdx == pools.length) {
                    putIdx = 0;
                }
                ++count;
                read.signal();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public Object take() {
        if (lock.tryLock()) {
            try {
                while (count == 0) {
                    read.await();
                }
                Object obj = pools[takeIdx];
                if (++takeIdx == pools.length) {
                    takeIdx = 0;
                }
                --count;
                write.signal();
                return obj;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        return null;
    }
}