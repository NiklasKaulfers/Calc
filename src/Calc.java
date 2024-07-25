package src;
import java.util.ArrayList;

/**
 * the calculator using the Tokens created earlier
 */
public class Calc{
    /**
     * calculates the result using an ArrayList of Tokens usually created by the Parser
     * @param tokens the Tokens 
     * @return the result based on user input
     */
    public double calculate(ArrayList<Token> tokens){
        double value1 = 0;
        Operator operator; 
        double value2 = 0;
        int indexOfNextOperator = 1;
        while (indexOfNextOperator > 0){
            indexOfNextOperator = findNextMulOrDiv(tokens);
        }
        // TODO: this is faulty and will be changed
        double result = solve(value1, value2, Operator.PLUS);
        return result;
    }

    /**
     * helper function which uses 2 numbers and an operator to solve this part of the calculation
     * @param x first number
     * @param y second number
     * @param operator used operator based on the Operator enum
     * @return a result for recycling in calculate
     */
    private double solve(double x, double y, Operator operator){
        double result;
        switch (operator){
            case PLUS:
                result = x + y;
                break;
            case MINUS:
                result = x - y;
                break;
            case MULTIPLY:
                result = y * x;
                break;
            case MUL:
                result = y * x;
                break;
            case DIVIDE:
                if (y == 0){
                    result = 0;
                    System.err.println("No Division with 0");
                } else {
                    result = x / y;
                }
                break;
            default: 
                result = 0;
        }

        return result;
    }

    /**
     * finds the next multiplication or dividation operator
     * @param tokens the tokens to iterate through
     * @return index of next MUL or DIV
     */
    private int findNextMulOrDiv(ArrayList<Token> tokens){
        for (int i = 0; i < tokens.size(); i++){
            Token t = tokens.get(i);
            if (t.isOperation() 
            && (t.getOperation().equals(Operator.MULTIPLY)
                || t.getOperation().equals(Operator.MUL)
                || t.getOperation().equals(Operator.DIVIDE) )){
                    return i;
               }
        }
        return -1;
    }

}