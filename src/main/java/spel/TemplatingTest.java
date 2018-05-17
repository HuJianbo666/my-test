package spel;

import org.junit.Test;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author Hu Jianbo
 * @date: 2018/5/17
 */
public class TemplatingTest {

    ExpressionParser parser = new SpelExpressionParser();

    @Test
    public void test1() {
        String randomPhrase =
                parser.parseExpression("random number is #{T(java.lang.Math).random()}",
                        new TemplateParserContext()).getValue(String.class);
        System.out.println(randomPhrase);
        // evaluates to "random number is 0.7038186818312008"
    }

    private class TemplateParserContext implements ParserContext {

        @Override
        public String getExpressionPrefix() {
            return "#{";
        }

        @Override
        public String getExpressionSuffix() {
            return "}";
        }

        @Override
        public boolean isTemplate() {
            return true;
        }
    }
}
