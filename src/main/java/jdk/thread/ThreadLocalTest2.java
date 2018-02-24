package jdk.thread;

/**
 * 多线程下并发问题demo
 * <p>
 * Created by hujianbo on 2018/2/24.
 */
public class ThreadLocalTest2 {
    public static void main(String[] args) throws InterruptedException {
        Thread2 thread2 = new Thread2();

        new Thread(thread2, "T1").start();
        new Thread(thread2, "T2").start();
    }

    private static class Thread2 implements Runnable {

        private int count = 0;

        @Override
        public void run() {
            count++;
            System.out.println(Thread.currentThread().getName() + " : " + count);
        }
    }
}

class ThreadLocal2 {
    
    public static void main(String[] args) {
        Thread3 thread3 = new Thread3();
        new Thread(thread3, "T1").start();
        new Thread(thread3, "T2").start();
    }

    private static class Thread3 implements Runnable {

        ThreadLocal<Integer> localCnt = new ThreadLocal<>();

        @Override
        public void run() {
            if (localCnt.get() == null) {
                localCnt.set(0);
            } else {
                localCnt.set(localCnt.get().intValue() + 1);
            }
            System.out.println(Thread.currentThread().getName() + " : " + localCnt.get().intValue());

        }
    }
}