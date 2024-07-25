package src;
public class Token{
    String value;
    boolean isOperation = false;
    Operator op;
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
}