package juc.locks;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport线程挂起和唤醒
 */
public class LockSupportTest {
    public static void main(String[] args) throws InterruptedException {
        final Object obj = new Object();
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for (int i = 0; i < 10; i++) {
                    sum += i;
                }
                LockSupport.park();
                System.out.println(sum);
            }
        }, "A");
        A.start();
        LockSupport.unpark(A);
    }
}
