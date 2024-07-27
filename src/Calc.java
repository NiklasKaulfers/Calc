package src;
import java.util.ArrayList;

/**
 * the calculator using the Tokens created earlier
 */
public class Calc{
    private ArrayList<Token> tokens;

    public static final String CALC_ERROR_MESSAGE_DIV_0 = "No division with 0.";

    /**
     * calculates the result using an ArrayList of Tokens usually created by the Parser
     * @param tokens the Tokens 
     * @return the result based on user input
     */
    public double calculate(ArrayList<Token> tokens){
        this.tokens = tokens;

        ArrayList<Token> partToSolve;
        int amountBrackets = findBracketsAmount();
        while (amountBrackets > 0){
            partToSolve = new ArrayList<>();
            int[] positions = findBrackets();
            for (int i = 0; i < positions[1] - positions[0] - 1; i++){  
                partToSolve.add(tokens.get(positions[0] + i + 1));
            }
            Token partResult = calcPart(partToSolve);
            for (int i = 0; i < positions[1] - positions[0] ; i++){
                tokens.remove(positions[0]);                        
            }
            
            tokens.set(positions[0], partResult);
            amountBrackets -= 1;
        }

        Token result = calcPart(tokens);
        
        return result.getParsedValue();
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
                    throw new Error(CALC_ERROR_MESSAGE_DIV_0);
                } else {
                    result = x / y;
                }
                break;
            case MODULO:
                if (y == 0){
                    throw new Error(CALC_ERROR_MESSAGE_DIV_0);
                } else {
                    result = x % y;
                }
                break;
            default: 
                result = 0;
        }
        return result;
    }

    /**
     * finds the next Operation with a higher priority than plus and minus
     * @param tokenList the tokens to iterate through
     * @return index of next MUL or DIV
     */
    private int findNextHighPrioOperation(ArrayList<Token> tokenList){
        for (int i = 0; i < tokenList.size(); i++){
            Token t = tokenList.get(i);
            if (t.isOperation() ){
                if (t.getOperation().equals(Operator.MULTIPLY)
                || t.getOperation().equals(Operator.DIVIDE)
                || t.getOperation().equals(Operator.MODULO)){
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * finds the amount of opened brackets
     * @return amount
     */
    private int findBracketsAmount(){
        int amount = 0;
        for (Token t: tokens){
            if (t.isOperation() 
            && t.getOperation().equals(Operator.OPEN_BRACKET))
                amount++;
        }
        return amount;
    }

    /**
     * finds start and end of a bracket and solves that operation before anything else
     * @return positions of the brackets
     */
    private int[] findBrackets(){
        int[] positions = new int[2];
        positions[0] = -1;
        positions[1] = -1;
        for (int i = 0; i < tokens.size(); i++){
            if (tokens.get(i).getValue().equals("(")){
                positions[0] = i;
            }
            if (tokens.get(i).getValue().equals(")")){
                positions[1] = i;
                return positions;
            }
        }
        return positions;
    }

    /**
     * helper function
     * @param tokensForCalcPart a copy of the tokens, which doesnt use the original
     * @return solved value
     */
    private Token calcPart(ArrayList<Token> tokensForCalcPart){
        double value1, value2, result = 0;
        Operator operator; 
        int indexOfNextOperator;

        while (tokensForCalcPart.size() >= 3){
            indexOfNextOperator = findNextHighPrioOperation(tokensForCalcPart);
            if (indexOfNextOperator == -1){
                break;
            }
            value1 = Double.parseDouble(tokensForCalcPart.get(indexOfNextOperator - 1 ).getValue());
            value2 = Double.parseDouble(tokensForCalcPart.get(indexOfNextOperator + 1).getValue());
            operator = tokensForCalcPart.get(indexOfNextOperator).getOperation();
            result = solve(value1, value2, operator);
            tokensForCalcPart.set(indexOfNextOperator - 1, new Token(result + ""));
            // removes it twice so everything used in this calculation gets removed except the result
            tokensForCalcPart.remove(indexOfNextOperator);
            tokensForCalcPart.remove(indexOfNextOperator);
        }

        while (tokensForCalcPart.size() >= 3){
            value1 = Double.parseDouble(tokensForCalcPart.get(0).getValue());
            value2 = Double.parseDouble(tokensForCalcPart.get(2).getValue());
            operator = tokensForCalcPart.get(1).getOperation();
            result = solve(value1, value2, operator);
            // position 0 = result , position 1 & 2 get deleted
            tokensForCalcPart.set(0, new Token(result));
            tokensForCalcPart.remove(1);
            tokensForCalcPart.remove(1);
        }
        return tokensForCalcPart.getFirst();
    }
}