package jdk.thread;

/**
 * 最基本的用法
 * Created by hujianbo on 2018/io1/26.
 */
public class ThreadTest1 {
    public static void main(String[] args) {
        new Thread(new Thread1(), "A").start();
    }

    private static class Thread1 implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "-" + i);
            }
        }
    }
}
