package jdk;

import java.util.Arrays;

/**
 * 模拟String
 * Created by hujianbo on 2018/3/1.
 */
public class MyString {

    private Object object;

    private final char[] value;

    public MyString() {
        this.value = "".toCharArray();
    }

    public MyString(String str) {
        this.value = str.toCharArray();
    }

    public MyString(char[] value) {
        this.value = Arrays.copyOf(value, value.length);
    }

    public int length() {
        return value.length;
    }

    public boolean isEmpty() {
        return value.length == 0;
    }

    public char[] toCharArray() {
        char[] result = new char[value.length];
        System.arraycopy(value, 0, result, 0, result.length);
        return result;
    }

    public boolean startwith(String prefix) {
        return startwith(prefix, 0);
    }

    public boolean startwith(String prefix, int toffset) {

        char[] fromChar = prefix.toCharArray();
        char[] toChar = new char[fromChar.length];

        System.arraycopy(value, toffset, toChar, 0, fromChar.length);

        if (toChar.length == fromChar.length) {
            for (int i = 0; i < toChar.length; i++) {
                if (fromChar[i] != toChar[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object anObject) {

        if (this == anObject) {
            return true;
        }
        if (anObject instanceof MyString) {
            MyString anObjectStr = (MyString) anObject;
            char[] chars = anObjectStr.toCharArray();
            // 首先字符长度要一致
            if (chars.length == value.length) {
                // 循环比较字符
                for (int i = 0; i < chars.length; i++) {
                    if (chars[i] != value[i]) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        MyString s1 = new MyString("s1");
        MyString s2 = new MyString("s11");
        boolean equals = s1.equals(s2);
        System.out.println(equals);

        System.out.println(s2.startwith("1", 1));
    }
}
