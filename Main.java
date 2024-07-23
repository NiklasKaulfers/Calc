import java.util.ArrayList;

public class Main{
    public static void main(String[] args){
        String s = args[0];
        Parser p = new Parser();
        Calc c = new Calc();
        ArrayList<Token> tokens = p.createTokenList(s);
        System.out.println(c.calculate(tokens));
    }       
}