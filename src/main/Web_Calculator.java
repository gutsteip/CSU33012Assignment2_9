package main;

import java.util.*;
import java.lang.Math;

public class Web_Calculator {

    public static float evaluateExpression(String exp) {
        Stack<Float> numStack = new Stack<Float>();
        Stack<Character> opStack = new Stack<Character>();
        boolean isLnNumber = false;
        boolean isExpNumber = false;
        /*
         * Part 1:
         * -Evaluate expressions in parentheses first
         * -Convert strings to numbers, put on numStack
         * -Put low priority operators on opStack
         * -If higher priority operators, pop numbers and do that
         */

        String expression = evalParentheses(exp);

        String currNum = "";
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            // Get final number and push to the number stack
            if (i == expression.length() - 1) {
                // Don't add parentheses to stack
                if (c != ')')
                    currNum += c;

                numStack.push(Float.valueOf(currNum));
            }
            // If it is digit, add to current number
            else if (isNumber(c) || c == '.')
                currNum += c;
            // Logrithmic function
            else if (c == 'l') {
                isLnNumber = true;
                i++;
            }
            // Exponential function
            else if (c == 'e') {
                isExpNumber = true;
                i += 2;
            }
            // If it's operator
            else if (isOperator(c)) {
                // Convert string to float
                float valOfCurrNum = Float.valueOf(currNum);

                // Check to see if valOfCurrNum needs to be ln or exp
                if (isExpNumber)
                    valOfCurrNum = (float) Math.exp(valOfCurrNum);
                if (isLnNumber)
                    valOfCurrNum = (float) Math.log(valOfCurrNum);

                // Reset those variables right after
                isExpNumber = false;
                isLnNumber = false;

                // Push current number (which has ended) to stack & reset currNum
                numStack.push(valOfCurrNum);
                currNum = "";

                // If operator stack isn't empty
                if (!opStack.isEmpty()) {
                    // If multiplication is on stack, do it before adding another operator
                    if (priority(opStack.peek()) >= priority(c)) {
                        // Get
                        float num1 = numStack.pop();
                        float num2 = numStack.pop();
                        char operator = opStack.pop();
                        numStack.push(doCalc(num1, num2, operator));
                        // Push new operator on stack
                        opStack.push(c);
                    } else
                        opStack.push(c);
                }
                // If opStack empty, just push operator to stack
                else
                    opStack.push(c);
            }
        }
        // If last number was exp/ln
        if (isExpNumber)
            numStack.push((float) Math.exp(numStack.pop()));
        if (isLnNumber)
            numStack.push((float) Math.log(numStack.pop()));
        /*
         * Part 2:
         * Do repeated operations until opStack is empty
         */
        while (!opStack.isEmpty()) {
            // Get two most recent numbers and their operator
            float num1 = numStack.pop();
            float num2 = numStack.pop();
            char operator = opStack.pop();
            // Do calculation and push it onto stack
            numStack.push(doCalc(num1, num2, operator));
        }
        // Return final number on stack (the final result of equation)
        return numStack.pop();
    }

    public static String evalParentheses(String expression) {
        String newExp = expression;
        int numOpenParentheses = 0;
        int numCloseParentheses = 0;
        int indexCloseParen = 0;

        for (int i = expression.length() - 1; i >= 0; i--) {
            if (expression.charAt(i) == ')') {
                indexCloseParen = i;
                numCloseParentheses++;
            } else if (expression.charAt(i) == '(') {
                numOpenParentheses++;
                if (numOpenParentheses == numCloseParentheses) {
                    // Get two strings that will be important
                    String insideWithParen = expression.substring(i, indexCloseParen + 1);
                    String insideParen = expression.substring(i + 1, indexCloseParen);

                    // Evaluate expression inside of parentheses (will call recursively)
                    String evaledParentheses = Float.toString(evaluateExpression(insideParen));

                    // Replace the string with the new String
                    newExp = newExp.replace(insideWithParen, evaledParentheses);
                }
            }
        }
        return newExp;
    }

    public static float doCalc(float num1, float num2, char operator) {
        if (operator == '*')
            return num1 * num2;
        else if (operator == '/')
            return num2 / num1;
        else if (operator == '+')
            return num1 + num2;
        else if (operator == '-')
            return num2 - num1;
        else // (operator == '^')
            return (float) Math.pow(num2, num1);
    }

    // For getting operator priority (power > mult = div > sub = add)
    public static int priority(char c) {
        if (c == '^')
            return 2;
        else if (c == '*' || c == '/')
            return 1;
        else
            return 0;
    }

    /*
     * Returns int code based on result
     * 0 = isValidExpression
     * 1 = isn't valid: operator issue or empty expression
     * 2 = isn't valid: unknown character
     * 3 = isn't valid: decimal issue
     * 4 = isn't valid: leading 0
     * 5 = parentheses issue
     */
    public static int isValidExpression(String expression) {
        boolean canBeOperator = false;
        boolean lastCharDecimal = false;
        boolean numHasDecimal = false;
        boolean hasHangingParen = false;
        boolean hasNumber = false;
        int numUnclosedParen = 0;
        // Single character expression
        if (expression.length() == 1) {
            if (isNumber(expression.charAt(0)))
                return 0;
            else
                return 1;
        }
        // Go through expression, if not valid, return false
        for (int i = 0; i < expression.length(); i++) {
            char currChar = expression.charAt(i);
            // System.out.println(i + " " + currChar);
            // Make sure no hanging decimals
            if (lastCharDecimal && !isNumber(currChar))
                return 3;
            // If it's an operator
            if (isOperator(currChar)) {
                // No decimal followed by operator (e.g: 3.+5)
                if (lastCharDecimal)
                    return 3;
                else {
                    lastCharDecimal = false;
                    numHasDecimal = false;
                }

                // If it isn't proper operator placement
                if (!canBeOperator)
                    return 1;
                // Final charatcer cannot be operator
                else if (i == expression.length() - 1)
                    return 1;
                // Otherwise, set canBeOperator to false (prevent double operators)
                else
                    canBeOperator = false;
            }
            // If it's a number, reset canBeOperator
            else if (isNumber(currChar)) {
                hasNumber = true;
                // Zero handling
                if (currChar == '0') {
                    // Leading 0 handling (make sure expression is 0+/-/* ... OR 0.SOMETHING)
                    if (i == 0) {
                        if (!isOperator(expression.charAt(i + 1))) {
                            if (expression.charAt(i + 1) != '.')
                                return 4;
                        }
                    }
                    // Check if expression isn't +/-/*0... (invalid)
                    else if (isOperator(expression.charAt(i - 1))) {
                        // Not final character in string (avoid errors)
                        if (i < expression.length() - 1) {
                            // If expression isn't operating on 0 (e.g. +0+) OR If next character isn't
                            // decimal (ex 0.5)
                            if (!isOperator(expression.charAt(i + 1))) {
                                if (expression.charAt(i + 1) != '.')
                                    return 4;
                            }
                        }
                    }
                }
                canBeOperator = true;
                lastCharDecimal = false;
            }
            // If it is a decimal, make sure everything works
            else if (currChar == '.') {
                // No double decimals (e.g. .. OR 34.56.78)
                if (lastCharDecimal || numHasDecimal)
                    return 3;
                // Make sure no trailing decimals or decimals after operators
                if ((i != 0 && isOperator(expression.charAt(i - 1))) || i == expression.length() - 1)
                    return 3;
                lastCharDecimal = true;
                numHasDecimal = true;
            }
            // Exp
            else if (currChar == 'e') {
                if (!expression.substring(i, i + 4).equals("exp("))
                    return 2;
                // No decimal followed by operator (e.g: 3.+exp(5))
                if (lastCharDecimal)
                    return 3;
                // Make sure no exp(+5) (invalid expression)
                canBeOperator = false;
                // Make sure no 5exp(45) (invalid without operator)
                if (i != 0 && isNumber(expression.charAt(i - 1)))
                    return 1;
                // Increment the counter to skip over the next letter & reset lastCharDecimal
                i += 2;
                lastCharDecimal = false;
            }
            // Log
            else if (currChar == 'l') {
                if (!expression.substring(i, i + 3).equals("ln("))
                    return 2;
                // No decimal followed by operator (e.g: 3.+ln(5))
                if (lastCharDecimal)
                    return 3;
                // Make sure no ln(+5) (invalid expression)
                canBeOperator = false;

                // Make sure no 5ln(45) (invalid without operator)
                if (i != 0 && isNumber(expression.charAt(i - 1)))
                    return 1;
                // Increment the counter to skip over the next two letters & reset
                // lastCharDecimal
                i++;
                lastCharDecimal = false;
            }
            // Parentheses
            else if (currChar == '(') {
                hasHangingParen = true;
                numUnclosedParen++;
            } else if (currChar == ')') {
                lastCharDecimal = false;
                if (!hasHangingParen)
                    return 5;
                numUnclosedParen--;
                if (numUnclosedParen == 0)
                    hasHangingParen = false;
                if (isOperator(expression.charAt(i - 1)))
                    return 1;
            }
            // If it isn't a valid character
            else
                return 2;
        }
        // If no number in expression
        if (!hasNumber)
            return 1;
        // If uneven parenthesis
        if (numUnclosedParen != 0 || hasHangingParen)
            return 5;
        // Check to see if last character ISN'T operator
        if (isOperator(expression.charAt(expression.length() - 1)))
            return 1;
        // Else, return 0 (is valid expression)
        return 0;
    }

    public static boolean isOperator(char currChar) {
        if (currChar == '+' || currChar == '-' || currChar == '*' || currChar == '/' || currChar == '^')
            return true;
        return false;
    }

    public static boolean isNumber(char currChar) {
        if (currChar == '1' || currChar == '2' || currChar == '3' || currChar == '4' || currChar == '5' ||
                currChar == '6' || currChar == '7' || currChar == '8' || currChar == '9' || currChar == '0')
            return true;
        return false;
    }
}
