package src;
public enum Operator{
    PLUS("+", '+'),
    MINUS("-", '-'),
    MULTIPLY("*", '*'),
    MUL("MUL", '*'),
    DIVIDE("/", '/');
    private String operation;
    private char operationAsChar;
    private Operator(String operation, char operationAsChar){
        this.operation = operation;
        this.operationAsChar = operationAsChar;
    }
    public String getValue(){
        return operation;
    }
    public char getValueAsChar(){
        return operationAsChar;
    }
}