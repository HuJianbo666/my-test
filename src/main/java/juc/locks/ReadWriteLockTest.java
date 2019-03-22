package juc.locks;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 *
 * @author BG340927
 */
public class ReadWriteLockTest {

    private final static ReentrantReadWriteLock LOCK = new ReentrantReadWriteLock();

    public static void main(String[] args) {

        ThreadPoolExecutor pool = new ThreadPoolExecutor(2,
                3,
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
        pool.execute(new Runnable() {
            @Override
            public void run() {
                read(Thread.currentThread());
            }
        });

        pool.execute(new Runnable() {
            @Override
            public void run() {
                write(Thread.currentThread());
            }
        });

        pool.shutdown();
    }

    private static void write(Thread thread) {
        LOCK.writeLock().lock();
        boolean writeLocked = LOCK.isWriteLocked();
        if (writeLocked) {
            System.out.println("当前为写锁");
        }
        try {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName() + ":正在进行写操作...");
            }
            System.out.println(thread.getName() + ":写操作完毕");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("释放写锁");
            LOCK.writeLock().unlock();
        }
    }

    private static void read(Thread thread) {
        LOCK.readLock().lock();
        boolean writeLocked = LOCK.isWriteLocked();
        if (!writeLocked) {
            System.out.println("当前为读锁");
        }
        try {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(20);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName() + "：正在进行读操作...");
            }
            System.out.println(thread.getName() + ":读操作完毕。");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("释放读锁。");
            LOCK.readLock().unlock();
        }
    }

}
