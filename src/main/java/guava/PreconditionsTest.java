package guava;

import com.google.common.base.Preconditions;

/**
 * 前置条件Preconditions提供静态方法来检查方法或构造函数，被调用是否给定适当的参数。
 * checkArgument
 * checkState
 * checkNotNull
 * checkElementIndex
 * <p>
 * Created by hujianbo on 2018/io1/25.
 */
public class PreconditionsTest {
    public static void main(String[] args) {
        try {
            getValue(5);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        try {
            sum(4, null);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }

    private static int getValue(int input) {
        int[] data = {1, 2, 3, 4, 5};
        int index = Preconditions.checkElementIndex(input, data.length,
                "Illegal Argument passed: Invalid index.");
        return data[index];
    }

    private static int sum(Integer i1, Integer i2) {

        i1 = Preconditions.checkNotNull(i1, "相加不可为null");
        i2 = Preconditions.checkNotNull(i2, "相加不可为null");
        return i1 + i2;
    }
}
