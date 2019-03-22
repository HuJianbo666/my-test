package juc.tools;

import juc.ThreadPoolFactory;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 信号量
 * 场景：
 * 假若一个工厂有5台机器，但是有8个工人，一台机器同时只能被一个工人使用，只有使用完了，其他工人才能继续使用。
 *
 * @author HuJianbo
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        ThreadPoolExecutor pool = ThreadPoolFactory.getDefaultThreadPool();
        Semaphore semaphore = new Semaphore(5);
        int num = 8;
        for (int i = 0; i < num; i++) {
            pool.execute(new Worker(semaphore, i));
        }
        pool.shutdown();
    }

    private static class Worker implements Runnable {
        Semaphore semaphore;
        int index;

        Worker(Semaphore semaphore, int index) {
            this.semaphore = semaphore;
            this.index = index;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("工人" + index + "使用一台机器");
                TimeUnit.SECONDS.sleep(4);
                System.out.println("工人" + index + "使用完这台机器了");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
