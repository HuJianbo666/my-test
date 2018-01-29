package regex;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by hujianbo on 2018/1/29.
 */
public class Test2 {
    public static void main(String[] args) {
        String content = "15011111111";
        //匹配 手机号段只有 13xxx 15xxx 18xxxx
        String reg = "1[358]\\d{9}";
        boolean matches = Pattern.matches(reg, content);
        //System.out.println(matches);

        String str = "avg   bb   geig   glsd   abc";
        String reg2 = " +";
        //分割
        String[] split = str.split(reg2);
        Arrays.asList(split).forEach(p -> System.out.println(p));
    }
}
