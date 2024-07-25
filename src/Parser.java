package src;
import java.util.ArrayList;

class Parser{
    /**
     * function that transforms a String representing the calculation into a List of Tokens
     * @param in the Calculation as String
     * @return the Calculation as ArrayList of Token
     */
    public ArrayList<Token> createTokenList(String in){
        in = in.trim();
        ArrayList<Token> tokens = new ArrayList<>();
        while (in.length() > 0){
            int indexOfNextOperator = findNextOperator(in);
            if (indexOfNextOperator == -1){
                tokens.add(new Token(in));
                in = "";
            } else {
                tokens.add(new Token(in.substring(0, indexOfNextOperator)));
                tokens.add(new Token(in.substring(indexOfNextOperator, indexOfNextOperator+1)));
                in = in.substring(indexOfNextOperator+1);         
            }
            
        }
        return tokens;
    }

    /**
     * private helper function to find the position of the next operator
     * @param in the remeianing String part
     * @return the index of the next operator
     */
    private int findNextOperator(String in){
        // maybe as set, but i like array lists :D
        ArrayList<Operator> operators = new ArrayList<>();
        for (Operator o : Operator.values()){
            operators.add(o);
        }
        for (int i = 0; i < in.length(); i++){
            final int finalI = i; 
            if (operators.stream().anyMatch(op -> op.getValueAsChar() == in.charAt(finalI))){
                return i;
            }
        }
        return -1;
    }
}