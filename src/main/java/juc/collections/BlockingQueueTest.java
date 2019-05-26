package juc.collections;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author HuJianbo
 */
public class BlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue synchronousQueue = new SynchronousQueue<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 40, 10, TimeUnit.SECONDS, synchronousQueue);
        for(int i = 0 ; i < 30; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {

                }
            });
        }
        threadPoolExecutor.shutdown();
    }
}
