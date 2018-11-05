package juc.cas;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/11/5 0005 上午 8:44
 */
public class Counter {
    private AtomicInteger atomicI = new AtomicInteger(0);
    private int i = 0;

    public static void main(String[] args) {
        final Counter cas = new Counter();
        List<Thread> ts = new ArrayList<Thread>(600);
        long start = System.currentTimeMillis();

        for(int j = 0; j < 100; j++) {
            Thread t = new Thread(() -> {
               for(int i =0; i < 10000; i++) {
                   cas.count();
                   cas.safeCount();
               }
            });

            ts.add(t);
        }

        for (Thread t : ts) {
            t.start();
        }

        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("i" + cas.i);
        System.out.println("cas:i" + cas.atomicI.get());
        long use = System.currentTimeMillis() - start;
        System.out.println("use" + use);
    }

    private void count() {
        i++;
    }
    private void safeCount() {
        for(;;) {
            int i = atomicI.get();
            boolean b = atomicI.compareAndSet(i, ++i);
            if (b) {
                break;
            }
        }

    }
}
