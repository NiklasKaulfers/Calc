package src;
/**
 * class defying a Token usable for Calc
 */
public class Token{
    private String value;
    private boolean isOperation = false;
    private Operator op;
    /**
     * Token is created with a String 
     * @param value a String of numbers or the Respectable Operation
     */
    public Token(String value){
        this.value = value;
        for (Operator o : Operator.values()){
            if (o.getValue().equals(value)){
                this.value = o.getValue();
                this.op = o;
                isOperation = true;
            }
        }
    }
    public String getValue(){
        return value;
    }
    /**
     * gets the double value of the String and returns 0 if the Token is an Operation
     * @return double value of Token
     */
    public double getParsedValue(){
        if (!isOperation){
            return Double.parseDouble(value);
        } else {
            return 0;
        }
    }
    /**
     * gets the Operation, returns null if its not an Operation
     * @return Operation thats stored in the Token
     */
    public Operator getOperation(){
        if (isOperation){
            return op;
        } else {
            return null;
        }

    }
    public boolean isOperation(){
        return isOperation;
    }
}