package thread;

/**
 * volatile一般用于状态判断，不适用于计算，例如i++运算，不能使用volatile，volatile并不能保证原子性。所以，除了状态参数可以使用volatile，其他的还是使用synchronized
 *
 * @author Hu Jianbo
 * @date: 2018/7/8
 */
public class VolatileDemo {
    private static volatile boolean isOver = false;

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isOver) ;
            }
        });
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isOver = true;
    }
}
