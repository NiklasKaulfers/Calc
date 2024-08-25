package src.tests;
import org.junit.*;

import java.util.ArrayList;


import src.Calc;
import src.Parser;
import src.Token;

// Assert.assertEquals(expected, actual, delta)
public class Tests {
    Calc c;
    Parser p;
    @Test
    public void testCalcWithBrackets1(){
        c = new Calc();
        p = new Parser();
        double result = c.calculate(p.createTokenList("(2+1)"));
        Assert.assertEquals(3, result, 0);
        result = c.calculate(p.createTokenList("12*(3+2*4)"));
        Assert.assertEquals(132, result, 0);
        result = c.calculate(p.createTokenList("(3+2*4)*12"));
        Assert.assertEquals(132, result, 0);
        result = c.calculate(p.createTokenList("(12-2+(27*128912*((12+12+(((12+12)))*2))+2)/2)*0"));
        Assert.assertEquals(0, result, 0);
    }
    @Test
    public void testParser(){
        p = new Parser();
        ArrayList<Token> result = p.createTokenList("           2+2   ");
        ArrayList<Token> expectedResult = new ArrayList<>();
        expectedResult.add(new Token("2"));
        expectedResult.add(new Token("+"));
        expectedResult.add(new Token("2"));
        for (int i = 0; i < expectedResult.size();i++){
            Assert.assertEquals(expectedResult.get(i).getValue(), result.get(i).getValue());
        }
    }
    @Test 
    public void testBracketsSimple(){
        c = new Calc();
        p = new Parser();
        double result = c.calculate(p.createTokenList("2*(2+3)"));
        Assert.assertEquals(10, result, 0);
    }
    @Test
    public void testAdd(){
        c = new Calc();
        p = new Parser();
        double result = c.calculate(p.createTokenList("2+1"));
        Assert.assertEquals(3, result, 0);
    }
    @Test
    public void testAddWithFloatingPoint(){
        Calc c = new Calc();
        Parser p = new Parser();
        double result = c.calculate(p.createTokenList("0+0.1"));
        Assert.assertEquals(0.1, result, 0);
    }
    @Test
    public void subTest(){
        c = new Calc();
        p = new Parser();
        double result = c.calculate(p.createTokenList("0-1"));
        Assert.assertEquals(-1, result, 0);
        result = c.calculate(p.createTokenList("1-0"));
        Assert.assertEquals(1, result, 0);
        result = c.calculate(p.createTokenList("1000000000-1000000000"));
        Assert.assertEquals(0, result, 0);
    }
    @Test
    public void mulTest(){
        c = new Calc();
        p = new Parser();
        double result = c.calculate(p.createTokenList("100*0"));
        Assert.assertEquals(0, result, 0);
        result = c.calculate(p.createTokenList("0*100"));
        Assert.assertEquals(0, result, 0);
        result = c.calculate(p.createTokenList("0.5*0.5*0.5"));
        Assert.assertEquals(0.125, result, 0);
        result = c.calculate(p.createTokenList("1*1*1*1*1*1*1*1*1*1*1*1*1*1*0"));
        Assert.assertEquals(0, result, 0);
        result = c.calculate(p.createTokenList("3+2*4"));
        Assert.assertEquals(11, result, 0);
    }
    @Test
    public void divTest(){
        c = new Calc();
        p = new Parser();
        try {
            c.calculate(p.createTokenList("12/20/0"));
        } catch (Error e){
            Assert.assertEquals(Calc.CALC_ERROR_MESSAGE_DIV_0, e.getMessage());
        }
    }
    @Test 
    public void inputErrorTests(){
        p = new Parser();
        try {
            p.createTokenList("19+2/as");
        } catch (IllegalArgumentException e ){
            Assert.assertEquals(Parser.ILLEGAL_ARGUMENT_IN_SCANNER + 'a', e.getMessage());
        }
    }
}
