package spel;

import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import spel.classesused.Inventor;
import spel.classesused.PlaceOfBirth;

import java.util.GregorianCalendar;

/**
 * @author Hu Jianbo
 * @date: 2018/5/17
 */
public class SpelTest {

    ExpressionParser parser = new SpelExpressionParser();

    @Test
    public void test1() {
        Expression exp = parser.parseExpression("'Hello World'");
        String message = (String) exp.getValue();
        System.out.println(message);
    }

    @Test
    public void test2() {
        Expression exp = parser.parseExpression("'Hello World'.concat('!')");
        String message = (String) exp.getValue();
        System.out.println(message);

        Expression exp2 = parser.parseExpression("'Hello World'.bytes");

        byte[] bytes = (byte[]) exp2.getValue();
        System.out.println(bytes.length);
    }

    @Test
    public void test3() {
        // Create and set a calendar
        GregorianCalendar c = new GregorianCalendar();
        c.set(1856, 7, 9);

        //  The constructor arguments are name, birthday, and nationality.
        Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");

        Expression exp = parser.parseExpression("name");
        EvaluationContext context = new StandardEvaluationContext(tesla);

        String name = (String) exp.getValue(context);
        System.out.println(name);

        Expression exp2 = parser.parseExpression("name == 'Nikola Tesla'");
        boolean result = exp2.getValue(context, Boolean.class);  // evaluates to true
        System.out.println(result);

    }

    @Test
    public void test4() {
        // Create and set a calendar
        GregorianCalendar c = new GregorianCalendar();
        c.set(1856, 7, 9);

        //  The constructor arguments are name, birthday, and nationality.
        Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");

        Expression exp = parser.parseExpression("name");

        String name = (String) exp.getValue(tesla);
        System.out.println(name);

    }


    @Test
    public void test5() {
        GregorianCalendar c = new GregorianCalendar();
        c.set(1856, 7, 9);

        Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");
        EvaluationContext context = new StandardEvaluationContext(tesla);

        tesla.setPlaceOfBirth(new PlaceOfBirth("beijing"));
        // evals to 1856
        int year = (Integer) parser.parseExpression("birthdate.Year + 1900").getValue(context);

        String city = (String) parser.parseExpression("placeOfBirth.City").getValue(context);
    }

    @Test
    public void test6() {

        Inventor tesla = new Inventor("Nikola Tesla", "Serbian");
        StandardEvaluationContext context = new StandardEvaluationContext(tesla);
        context.setVariable("newName", "Mike Tesla");

        parser.parseExpression("Name = #newName").getValue(context);
        Boolean value = parser.parseExpression("birthdate.Year > 2").getValue(context, Boolean.class);

        System.out.println(tesla.getName()); // "Mike Tesla"

        Boolean v = parser.parseExpression("!(Name == 'Mike Tes2la' && birthdate.Year > 2)").getValue(context, Boolean.class);
        System.out.println(v);

    }

    @Test
    public void test7() {
        // Safe Navigation operator
        ExpressionParser parser = new SpelExpressionParser();

        Inventor tesla = new Inventor("Nikola Tesla", "Serbian");
        tesla.setPlaceOfBirth(new PlaceOfBirth("Smiljan"));

        StandardEvaluationContext context = new StandardEvaluationContext(tesla);

        String city = parser.parseExpression("PlaceOfBirth?.City").getValue(context, String.class);
        System.out.println(city); // Smiljan

        tesla.setPlaceOfBirth(null);

        city = parser.parseExpression("PlaceOfBirth?.City").getValue(context, String.class);

        System.out.println(city); // null - does not throw NullPointerException!!!
    }

}
