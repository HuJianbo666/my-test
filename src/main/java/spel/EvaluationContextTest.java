package spel;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hu Jianbo
 * @date: 2018/5/17
 */
public class EvaluationContextTest {

    /**
     * Type Conversion
     */
    @Test
    public void test1() {

        class Simple {
            public List<Boolean> booleanList = new ArrayList<Boolean>();
        }

        Simple simple = new Simple();

        simple.booleanList.add(true);

        StandardEvaluationContext simpleContext = new StandardEvaluationContext(simple);
        ExpressionParser parser = new SpelExpressionParser();

        // false is passed in here as a string.  SpEL and the conversion service will
        // correctly recognize that it needs to be a Boolean and convert it
        parser.parseExpression("booleanList[0]").setValue(simpleContext, "false");

        // b will be false
        Boolean b = simple.booleanList.get(0);
        assert b == false;
    }
}
