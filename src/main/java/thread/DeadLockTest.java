package thread;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Hu Jianbo
 * @Date: 2019/5/26 0026 上午 10:39
 */
public class DeadLockTest {

    public static void main(String[] args) {
        Runnable r1 = () -> {
            while (true) {
                lockAB();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable r2 = () -> {
            while (true) {
                lockBA();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(r1).start();
        new Thread(r2).start();
    }

    private static String A = "A";
    private static String B = "B";
    private static void lockAB() {
        synchronized (A) {
            System.out.println(Thread.currentThread().getName() + "A LOCKED");
            synchronized (B) {
                System.out.println(Thread.currentThread().getName() + "B LOCKED");
            }
        }
    }

    private static void lockBA() {
        synchronized (B) {
            System.out.println(Thread.currentThread().getName() + "B LOCKED");
            synchronized (A) {
                System.out.println(Thread.currentThread().getName() + "A LOCKED");
            }
        }
    }
}
