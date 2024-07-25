package src;
public enum Operator{
    /**
     * the plus operator
     */
    PLUS("+", '+'),
    /**
     * the minus operator
     */
    MINUS("-", '-'),
    /**
     * the multiplication operator
     */
    MULTIPLY("*", '*'),
    /**
     * the division operator
     */
    DIVIDE("/", '/');
    private String operation;
    private char operationAsChar;
    /**
     * constructs each operator
     * @param operation Symbol / Symbols representing the Operation
     * @param operationAsChar the symbol for single char useage
     */
    private Operator(String operation, char operationAsChar){
        this.operation = operation;
        this.operationAsChar = operationAsChar;
    }
    /**
     * gets the operator as String
     * @return the operator as String
     */
    public String getValue(){
        return operation;
    }
    /**
     * gets the operator as char
     * @return the operator as char
     */
    public char getValueAsChar(){
        return operationAsChar;
    }
}