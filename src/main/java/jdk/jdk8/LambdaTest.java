package jdk.jdk8;

/**
 * @author Hu Jianbo
 * @date: 2018/9/6
 */
public class LambdaTest {

    public static void main(String[] args) {
        LambdaAndInner lai = new LambdaAndInner();
        lai.test();
    }

}


@FunctionalInterface
interface Displayable {
    public void display();

    default int add(int a, int b) {
        return a + b;
    }
}

class LambdaAndInner {
    private int age = 10;
    private static String name = "Higgin";

    public void test() {
        String book = "《java编程思想》";
        Displayable dis = () -> {
            //访问"effectively final"局部变量
            System.out.println("book局部变量为：" + book);
            //访问外部类的实力变量和类变量
            System.out.println("外部类的实例变量age：" + age);
            System.out.println("外部类的类变量name:" + name);
        };
        dis.display();
        //调用dis对象从接口中继承的add()方法
        System.out.println(dis.add(10, 20));
    }
}