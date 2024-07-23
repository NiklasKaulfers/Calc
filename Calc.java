import java.util.ArrayList;


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
        if (Character.isDigit(tokens.get(0).getValue().charAt(0))){
                value1 = Double.parseDouble(tokens.get(0).getValue());            
        }
        if (tokens.get(1).isOperation){
            operator = tokens.get(1).getOperation();
        } else {
            System.err.println("Wrong operation");
            throw new Error();
        }
        if (Character.isDigit(tokens.get(2).getValue().charAt(0))){
            value2 = Double.parseDouble(tokens.get(2).getValue());
        }
        double result = solve(value1, value2, operator);
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

}