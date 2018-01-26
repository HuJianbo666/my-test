package jdk.thread;

/**
 * ThreadLocal类用于创建只能由同一个线程读取和写入的线程局部变量
 * <p>
 * Created by hujianbo on 2018/1/26.
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();

        Thread t1 = new Thread(thread1);
        Thread t2 = new Thread(thread1);
        Thread t3 = new Thread(thread1);
        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static class Thread1 implements Runnable {

        ThreadLocal<Integer> localInt = new ThreadLocal<>();
        int counter = 0;

        @Override
        public void run() {
            counter++;
            if (localInt.get() != null) {
                localInt.set(localInt.get().intValue() + 1);
            } else {
                localInt.set(0);
            }
            System.out.println("counter:" + counter);
            System.out.println("localint" + localInt.get());
        }
    }
}
