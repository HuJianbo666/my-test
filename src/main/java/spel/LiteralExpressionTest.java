package spel;

import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import spel.classesused.Inventor;
import spel.classesused.PlaceOfBirth;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Hu Jianbo
 * @date: 2018/5/17
 */
public class LiteralExpressionTest {

    ExpressionParser parser = new SpelExpressionParser();

    @Test
    public void test1() {
        ExpressionParser parser = new SpelExpressionParser();

        // evals to "Hello World"
        String helloWorld = (String) parser.parseExpression("'Hello World'").getValue();

        double avogadrosNumber = (Double) parser.parseExpression("6.0221415E+23").getValue();

        // evals to 2147483647
        int maxValue = (Integer) parser.parseExpression("0x7FFFFFFF").getValue();

        boolean trueValue = (Boolean) parser.parseExpression("true").getValue();

        Object nullValue = parser.parseExpression("null").getValue();
    }

}
