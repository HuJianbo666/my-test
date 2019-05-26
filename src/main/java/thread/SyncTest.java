package thread;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Hu Jianbo
 * @Date: 2019/5/26 0026 上午 10:10
 */
public class SyncTest {

    private static int cnt;
    public static void main(String[] args) throws InterruptedException {

        // 被锁对象
        ID id = new ID();
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + id.getCnt());
            System.out.println("r1");
        };
        Thread a = new Thread(r, "A");

        Runnable r2 = () -> {
            System.out.println(Thread.currentThread().getName() + id.getCnt());
            System.out.println("r2");
        };
        Thread b = new Thread(r2, "B");

        a.start();
        b.start();

    }

    private static class ID {
        private int cnt;

        public synchronized int getCnt() {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return cnt++;
        }

    }
}
