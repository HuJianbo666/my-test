package jdk.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by hujianbo on 2018/2/24.
 */
public class ReflectTest {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        InvocationHandler h = new MyInvocationHandler(userService);
        
        UserService o = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(), userService.getClass().getInterfaces(), h);

        o.m1();
        o.m2();
    }
}
