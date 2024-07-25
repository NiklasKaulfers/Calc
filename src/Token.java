package src;
public class Token{
    private String value;
    private boolean isOperation = false;
    private Operator op;
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
    public Operator getOperation(){
        return op;
    }
    public boolean isOperation(){
        return isOperation;
    }
}