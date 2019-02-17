/**
 * 单例,静态内部类
 *
 * @Author: Hu Jianbo
 * @Date: 2019/2/17 0017 上午 10:10
 */
public class Singleton {
    private Singleton() {
    }

    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getUniqueInstance() {
        return SingletonHolder.INSTANCE;
    }
}
