package thread;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Hu Jianbo
 * @Date: 2019/5/26 0026 上午 9:51
 */
public class JoinTest {
    private static int result;

    public static void main(String[] args) {
        Runnable r = () -> {
            result = compute(100);
        };
        Thread t = new Thread(r);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    private static int compute(int i) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i * 2;
    }
}
