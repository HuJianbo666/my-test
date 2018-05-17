package spel;

import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import spel.classesused.Inventor;
import spel.classesused.PlaceOfBirth;
import spel.classesused.Society;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Hu Jianbo
 * @date: 2018/5/17
 */
public class OperatorsTest {

    ExpressionParser parser = new SpelExpressionParser();

    @Test
    public void test1() {
        // evaluates to a Java list containing the four numbers
        List numbers = (List) parser.parseExpression("{1,2,3,4}").getValue();

        List listOfLists = (List) parser.parseExpression("{{'a','b'},{'x','y'}}").getValue();
    }

    @Test
    public void test2() {
        // evaluates to true
        Integer trueValue = parser.parseExpression("2 + 3 * (9 - 7) - 1").getValue(Integer.class);

        // evaluates to false
        boolean falseValue = parser.parseExpression("2 < -5.0").getValue(Boolean.class);

        // evaluates to true
        boolean trueValue2 = parser.parseExpression("'black' < 'block'").getValue(Boolean.class);

    }

    @Test
    public void test3() {
        // evaluates to false
        boolean falseValue = parser.parseExpression("'xyz' instanceof T(int)").getValue(Boolean.class);

        // evaluates to true
        boolean trueValue =
                parser.parseExpression("'5.00' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class);

        //evaluates to false
        boolean falseValue2 =
                parser.parseExpression("'5.0067' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class);

    }

    @Test
    public void test4() {

        GregorianCalendar c = new GregorianCalendar();
        c.set(1856, 7, 9);

        Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");
        Inventor Mihajlo = new Inventor("Mihajlo Pupin", c.getTime(), "China");

        tesla.setPlaceOfBirth(new PlaceOfBirth("NewYork"));
        Mihajlo.setPlaceOfBirth(new PlaceOfBirth("BeiJing"));

        Society societyContext = new Society();
        societyContext.addMember(tesla);
        societyContext.addMember(Mihajlo);


        // -- AND --

        // evaluates to false
        boolean falseValue = parser.parseExpression("true and false").getValue(Boolean.class);

        // evaluates to true
        String expression =  "isMember('Nikola Tesla') and isMember('Mihajlo Pupin')";
        boolean trueValue = parser.parseExpression(expression).getValue(societyContext, Boolean.class);

        // -- OR --

        // evaluates to true
        boolean trueValue2 = parser.parseExpression("true or false").getValue(Boolean.class);

        // evaluates to true
        String expression2 =  "isMember('Nikola Tesla') or isMember('Albert Einstein')";
        boolean trueValue3 = parser.parseExpression(expression).getValue(societyContext, Boolean.class);

        // -- NOT --

        // evaluates to false
        boolean falseValue2 = parser.parseExpression("!true").getValue(Boolean.class);


        // -- AND and NOT --
        String expression3 =  "isMember('Nikola Tesla') and !isMember('Mihajlo Pupin')";
        boolean falseValue3 = parser.parseExpression(expression).getValue(societyContext, Boolean.class);
    }
}
