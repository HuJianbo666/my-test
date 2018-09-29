package guava;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

/**
 * Created by hujianbo on 2018/3/io1.
 */
public class ImmutableTest {

    public static ImmutableSet<String> SecondNotCanRepeatRequestUrlSet = null;

    public static ImmutableList<Integer> unList = null;

    static {
        ImmutableSet.Builder<String> builder = new ImmutableSet.Builder<String>();
        builder.add("/Api-App/auth/borrow-apply");
        builder.add("/Api-App/auth/pay-confirm");
        builder.add("/Api-App/auth/verify-fail-confirm");
        builder.add("/Api-App/auth/repayment");
        builder.add("/Api-App/auth/repayment-confirm");
        SecondNotCanRepeatRequestUrlSet = builder.build();

        ImmutableList.Builder<Integer> list = new ImmutableList.Builder<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        unList = list.build();


    }

    public static void main(String[] args) {
        ImmutableList<Integer> unList = ImmutableTest.unList;
        Integer integer = unList.get(2);
        System.out.println(integer);
    }

}
