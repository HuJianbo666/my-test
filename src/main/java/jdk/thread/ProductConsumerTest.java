package jdk.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产消费demo
 * <p>
 * Created by hujianbo on 2018/1/26.
 */
public class ProductConsumerTest {

    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        new Thread(new Producter(clerk), "Producter").start();
        new Thread(new Consumer(clerk), "Consumer").start();

    }

    private static class Producter implements Runnable {

        Clerk clerk;

        Producter(Clerk clerk) {
            this.clerk = clerk;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                clerk.buy();
            }
        }
    }

    private static class Consumer implements Runnable {
        Clerk clerk;

        Consumer(Clerk clerk) {
            this.clerk = clerk;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                clerk.sale();
            }
        }
    }

    private static class Clerk {
        private int num = 0;

        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();


        public void buy() {
            lock.lock();
            try {
                if (num == 1) {
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + ": " + num++);
                condition.signalAll();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

        public void sale() {
            lock.lock();
            try {
                if (num == 0) {
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + ": " + num--);
                condition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
