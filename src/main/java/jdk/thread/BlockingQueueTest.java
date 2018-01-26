package jdk.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue一个demo
 * <p>
 * Created by hujianbo on 2018/1/26.
 */
public class BlockingQueueTest {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
        new Thread(new Producter(queue)).start();
        new Thread(new Consumer(queue)).start();

    }

    private static class Producter implements Runnable {

        BlockingQueue<Integer> queue;

        Producter(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                queue.put(1);
                System.out.println("put" + 1);
                //queue.put(2);
                //System.out.println("put" + 2);
                queue.put(3);
                System.out.println("put" + 3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private static class Consumer implements Runnable {
        BlockingQueue<Integer> queue;

        Consumer(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {

            try {
                System.out.println("take" + queue.take());
                System.out.println("take" + queue.take());
                System.out.println("take" + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
