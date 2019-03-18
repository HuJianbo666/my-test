package juc.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    public static void main(String[] args) throws InterruptedException {
        final BoundedBuffer boundedBuffer = new BoundedBuffer();

        Thread a = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("putting...:" + i);
                boundedBuffer.put(i);
            }
        }, "A");

        Thread b = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("taking...:" + boundedBuffer.take());
            }
        }, "B");
        a.start();
        Thread.sleep(1000);
        b.start();
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