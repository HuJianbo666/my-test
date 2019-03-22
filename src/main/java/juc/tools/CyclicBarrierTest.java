package juc.tools;

import juc.ThreadPoolFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 场景：
 * 假若有若干个线程都要进行写数据操作，并且只有所有线程都完成写数据操作之后，这些线程才能继续做后面的事情，
 * 可重用
 *
 * @author HuJianbo
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        // 如果设置parties大于线程数，所有到达barrier状态的线程都处于await状态
        CyclicBarrier barrier = new CyclicBarrier(4, () -> {
            System.out.println(Thread.currentThread().getName() + "所有线程到达barrier状态");
        });
        ThreadPoolExecutor pool = ThreadPoolFactory.getDefaultThreadPool();
        for (int i = 0; i < 4; i++) {
            pool.execute(new Writer(barrier));
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("barrier重用");

        for (int i = 0; i < 4; i++) {
            pool.execute(new Writer(barrier));
        }

        pool.shutdown();
    }

    private static class Writer implements Runnable {

        private CyclicBarrier cyclicBarrier;

        Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "正在进行写操作...");
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "写操作完成");
                cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
                e.printStackTrace();
            }
            System.out.println("所有线程写操作完成，继续处理其他任务");
        }
    }
}
