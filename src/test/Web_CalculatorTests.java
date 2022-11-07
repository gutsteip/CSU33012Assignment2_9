package test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.example.CalculatorApp;

public class Web_CalculatorTests {
    @Test
    public void testIsValidExpression() {
        String input = "";

        // test correct expression
        input = "12435+34569-12345*10+50";
        assertEquals("Checking correct expression", 0, Web_Calculator.isValidExpression(input));

        // test empty expression
        input = " ";
        assertEquals("Checking empty expression", 1, Web_Calculator.isValidExpression(input));

        // test single value
        input = "3";
        assertEquals("Checking single value", 0, Web_Calculator.isValidExpression(input));

        // test operator at the beginning
        input = "+5-7*5";
        assertEquals("Checking operator at the start", 1, Web_Calculator.isValidExpression(input));

        // test operator at the end
        input = "5-7*5+";
        assertEquals("Checking operator at the end", 1, Web_Calculator.isValidExpression(input));

        // test double operator
        input = "5-7*5++4 ";
        assertEquals("Checking double operator", 1, Web_Calculator.isValidExpression(input));

        // test unknown character
        input = "10/2 + f";
        assertEquals("Checking unknown character", 2, Web_Calculator.isValidExpression(input));

        // test decimal values
        input = "3.5*6+10.2";
        assertEquals("Checking decimal values", 0, Web_Calculator.isValidExpression(input));

        // test leading zeros
        input = "3+067-56";
        assertEquals("Checking leading zero", 4, Web_Calculator.isValidExpression(input));

        // test parentheses
        input = "(5+4)*7";
        assertEquals("Checking parentheses", 0, Web_Calculator.isValidExpression(input));

        // test parentheses issue
        input = "(5+4)*7)";
        assertEquals("Checking parentheses", 5, Web_Calculator.isValidExpression(input));

        // test exp and ln
        input = "exp(5+4)+ln(7)";
        assertEquals("Checking exp and ln", 0, Web_Calculator.isValidExpression(input));

    }

    @Test
    public void testEvaluateExpression() {
        String input = "15+20";
        assertEquals("Check basic addition", 35, Web_Calculator.evaluateExpression(input));

        input = "159-23";
        assertEquals("Check basic subtraction", 136, Web_Calculator.evaluateExpression(input));

        input = "4*16";
        assertEquals("Check basic multiplication", 64, Web_Calculator.evaluateExpression(input));

        input = "2+4*16";
        assertEquals("Check addition & multiplication", 66, Web_Calculator.evaluateExpression(input));

        input = "50+4-6*14";
        assertEquals("Check complex equation", -30, Web_Calculator.evaluateExpression(input));

        input = "4*5*2+3-10*3";
        assertEquals("Check complex equation", 13, Web_Calculator.evaluateExpression(input));

        input = "3*2-4+3*2";
        assertEquals("Check complex equation", 8, Web_Calculator.evaluateExpression(input));

        input = "10*(3+5)";
        assertEquals("Check complex equation", 80, Web_Calculator.evaluateExpression(input));

        input = "ln(exp(5))";
        assertEquals("Check exponential equation", 5, Web_Calculator.evaluateExpression(input));

        input = "3+5*exp(4.2)/(5+7)";
        assertEquals("Check exponential equation", 30.786, Web_Calculator.evaluateExpression(input));
    }
}
