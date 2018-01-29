package regex;

import java.util.regex.Pattern;

/**
 * Created by hujianbo on 2018/1/29.
 */
public class Test {
    public static void main(String[] args) {
        boolean b = checkQQ("11234");
        System.out.println(b);

    }

    /**
     * 校验QQ号，要求：必须是5~15位数字，0不能开头
     *
     * @param s
     * @return
     */
    private static boolean checkQQ(String s) {

        String reg = "[1-9][0-9]{4,14}";
        boolean matches = Pattern.matches(reg, s);
        return matches;
    }
}
