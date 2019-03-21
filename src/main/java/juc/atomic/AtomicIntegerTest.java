package juc.atomic;

import juc.ThreadPoolFactory;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger
 *
 * @author HuJianbo
 */
public class AtomicIntegerTest {
    private static AtomicInteger NUM = new AtomicInteger(0);
    private final static int RUN = 5;

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor pool = ThreadPoolFactory.getDefaultThreadPool();
        pool.execute(new Producter());
        pool.execute(new Consumer());
    }

    private static class Producter implements Runnable {
        private Clerk clerk = new Clerk();

        @Override
        public void run() {
            for (int i = 0; i < RUN; i++) {
                clerk.buy();
            }
        }
    }

    private static class Consumer implements Runnable {
        private Clerk clerk = new Clerk();

        @Override
        public void run() {
            for (int i = 0; i < RUN; i++) {
                clerk.sale();
            }
        }
    }

    private static class Clerk {

        public void buy() {
            System.out.println("buy:" + NUM.incrementAndGet());
        }

        public void sale() {
            System.out.println("sale:" + NUM.decrementAndGet());
        }

    }
}
