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
        int indexOfNextOperator;
        double result = 0;

        while (tokens.size() >= 3){
            indexOfNextOperator = findNextMulOrDiv(tokens);
            if (indexOfNextOperator == -1){
                break;
            }
            value1 = Double.parseDouble(tokens.get(indexOfNextOperator - 1 ).getValue());
            value2 = Double.parseDouble(tokens.get(indexOfNextOperator + 1).getValue());
            operator = tokens.get(indexOfNextOperator).getOperation();
            result = solve(value1, value2, operator);
            tokens.set(indexOfNextOperator - 1, new Token(result + ""));
            tokens.remove(indexOfNextOperator);
            tokens.remove(indexOfNextOperator);
        }
        while (tokens.size() >= 3){
            value1 = Double.parseDouble(tokens.get(0).getValue());
            value2 = Double.parseDouble(tokens.get(2).getValue());
            operator = tokens.get(1).getOperation();
            result = solve(value1, value2, operator);
            tokens.set(0, new Token(result + ""));
            tokens.remove(1);
            tokens.remove(1);
        }
        return Double.parseDouble(tokens.getFirst().getValue());
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
            if (t.isOperation() ){
                if (t.getOperation().equals(Operator.MULTIPLY)
                || t.getOperation().equals(Operator.DIVIDE)){
                    return i;
                }
            }
        }
        return -1;
    }

}