import java.util.*;

public class Web_Calculator {
    public static void main(String[] args)
    {
        String exp = "";
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter a mathematical expression, " + "\n" + "using addition, subtraction, multiplication, or division: \n");
        int valid = isValidExpression(exp);
        if(valid == 0)
        {
            int result = evaluateExpression(exp);
            System.out.println("The result is : " + result);
        }
        //Print out specific error message and reset exp
        else
        {
            if (valid == 1)
                System.out.println("Error: Operator issue or empty expression. Please try again.");
            else if (valid == 2)
                System.out.println("Error: Unknown character. Please try again.");
            else if (valid == 3)
                System.out.println("Error: Decimal number. Please try again.");
            else if (valid == 4)
                System.out.println("ERROR: Leading 0. Please try again.");
            else if (valid == 5)
                System.out.println("ERROR: Parentheses issue. Please try again.");
        }
        input.close();
    }

    public static int evaluateExpression(String expression)
    {
        Stack<Integer> numStack = new Stack<Integer>();
        Stack<Character> opStack = new Stack<Character>();
		/*
		Part 1:
		-Convert strings to numbers, put on numStack
		-Put + and - on opStack
		-If multiplication, pop numbers and do that
		*/
        String currNum = "";
        for(int i = 0; i < expression.length(); i++)
        {
            char c = expression.charAt(i);
            //Get final number and push to the number stack
            if(i == expression.length()-1)
            {
                currNum += c;
                numStack.push(Integer.valueOf(currNum));
            }
            //If it is digit, add to current number
            else if(isNumber(c))
                currNum += c;
                //If it's operator
            else if(isOperator(c))
            {
                //Push current number (which has ended) to stack & reset currNum
                numStack.push(Integer.valueOf(currNum));
                currNum = "";
                //If operator stack isn't empty
                if (!opStack.isEmpty())
                {
                    //If multiplication is on stack, do it before adding another operator
                    if(priority(opStack.peek()) >= priority(c))
                    {
                        //Get
                        int num1 = numStack.pop();
                        int num2 = numStack.pop();
                        char operator = opStack.pop();
                        numStack.push(doCalc(num1, num2, operator));
                        //Push new operator on stack
                        opStack.push(c);
                    }
                    else
                        opStack.push(c);
                }
                //If opStack empty, just push operator to stack
                else
                    opStack.push(c);
            }
        }
		/*
		 Part 2:
		 Do repeated addition/subtraction until opStack is empty
		 */
        while(!opStack.isEmpty())
        {
            //Get two most recent numbers and their operator
            int num1 = numStack.pop();
            int num2 = numStack.pop();
            char operator = opStack.pop();
            //Do calculation and push it onto stack
            numStack.push(doCalc(num1, num2, operator));
        }
        //Return final number on stack (the final result of equation)
        return numStack.pop();
    }

    public static int doCalc(int num1, int num2, char operator)
    {
        if(operator == '*')
            return num1 * num2;
        else if(operator == '/')
            return num2 / num1;
        else if(operator == '+')
            return num1 +  num2;
        else //subtraction
            return num2 - num1;
    }

    //For getting operator priority (mult = div > sub = add)
    public static int priority(char c)
    {
        if(c == '*' || c == '/')
            return 1;
        else
            return 0;
    }

    /*
     Returns int code based on result
     *0 = isValidExpression
     *1 = isn't valid: operator issue or empty expression
     *2 = isn't valid: unknown character
     *3 = isn't valid: decimal issue
     *4 = isn't valid: leading 0
     *5 = parentheses issue*/
    public static int isValidExpression(String expression)
    {
        boolean canBeOperator = false;
        boolean lastCharDecimal = false;
        boolean numHasChar = false;
        int numUnclosedParen = 0;
        //Single character expression
        if(expression.length() == 1)
        {
            if(isNumber(expression.charAt(0)))
                return 0;
            else
                return 1;
        }
        //Go through expression, if not valid, return false
        for(int i = 0; i < expression.length(); i++)
        {
            char currChar = expression.charAt(i);
            //If it's an operator
            if(isOperator(currChar))
            {
                //No decimal followed by operator (e.g: 3.+5)
                if(lastCharDecimal)
                    return 3;
                else
                {
                    lastCharDecimal = false;
                    numHasChar = false;
                }

                //If it isn't proper operator placement
                if(!canBeOperator)
                    return 1;
                    //Final charatcer cannot be operator
                else if(i == expression.length()-1)
                    return 1;
                    //Otherwise, set canBeOperator to false (prevent double operators)
                else
                    canBeOperator = false;
            }
            //If it's a number, reset canBeOperator
            else if(isNumber(currChar))
            {
                //Zero handling
                if(currChar == '0')
                {
                    //Leading 0 handling (make sure expression is 0+/-/* ...)
                    if(i == 0)
                    {
                        if(!isOperator(expression.charAt(i+1)))
                            return 4;
                    }
                    //Check if expression isn't +/-/*0... (invalid)
                    else if(isOperator(expression.charAt(i-1)))
                    {
                        //Not final character in string (avoid errors)
                        if(i < expression.length()-1)
                        {
                            //If expression isn't operating on 0 (e.g. +0+)
                            if(!isOperator(expression.charAt(i+1)))
                                return 4;
                        }
                    }
                }
                canBeOperator = true;
                lastCharDecimal = false;
            }
            //If it is a decimal, make sure everything works
            else if(currChar.equals('.'))
            {
                //No double decimals (e.g. .. OR 34.56.78)
                if(lastCharDecimal || numHasChar)
                    return 3;
                //Make sure no trailing decimals or decimals after operators
                if((i != 0 && !isOperator(expression.charAt(i-1))) || i != expression.length()-1)
                    return 3;
                lastCharDecimal = true;
                numHasChar = true;
            }
            //Exp
            else if(currChar.equals('e'))
            {
                if(!expression.substring(i, i+3).equals("exp"))
                    return 2;
                //No decimal followed by operator (e.g: 3.+5)
                if(lastCharDecimal)
                    return 3;
                //Make sure no e+5 (invalid expression)
                canBeOperator = false;
            }
            //Log
            else if(currChar.equals('l'))
            {
                if(!expression.substring(i, i+3).equals("log"))
                    return 2;
                //No decimal followed by operator (e.g: 3.+5)
                if(lastCharDecimal)
                    return 3;
                //Make sure no log+5 (invalid expression)
                canBeOperator = false;
            }
            //Parentheses
            else if(currChar.equals('('))
                numUnclosedParen++;
            else if(currChar.equals(')'))
                numUnclosedParen--;
            //If it isn't a valid character
            else
                return 2;
        }
        //If uneven parenthesis
        if(numUnclosedParen != 0)
            return 5;
        //Check to see if last character ISN'T operator
        if(isOperator(expression.charAt(expression.length()-1)))
            return 1;
        //Else, return 0 (is valid expression)
        return 0;
    }

    public static boolean isOperator(char currChar)
    {
        if(currChar == '+' || currChar == '-' || currChar == '*' || currChar == '/' || currChar == '^')
            return true;
        return false;
    }

    public static boolean isNumber(char currChar)
    {
        if(currChar == '1' ||currChar == '2' || currChar == '3' || currChar == '4' || currChar == '5' ||
                currChar == '6' || currChar == '7' ||currChar == '8' || currChar == '9' ||currChar == '0')
            return true;
        return false;
    }
}
