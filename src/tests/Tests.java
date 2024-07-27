package src.tests;
import org.junit.*;


import src.Calc;
import src.Parser;

// Assert.assertEquals(expected, actual, delta)
public class Tests {
    @Test
    public void testCalcWithBrackets1(){
        Calc c = new Calc();
        Parser p = new Parser();
        double result = c.calculate(p.createTokenList("(2+1)"));
        Assert.assertEquals(3, result, 0);
        result = c.calculate(p.createTokenList("12*(3+2*4)"));
        Assert.assertEquals(132, result, 0);
        result = c.calculate(p.createTokenList("(3+2*4)*12"));
        Assert.assertEquals(132, result, 0);
    }
    @Test 
    public void testBracketsSimple(){
        Calc c = new Calc();
        Parser p = new Parser();
        double result = c.calculate(p.createTokenList("2*(2+3)"));
        Assert.assertEquals(10, result, 0);
    }
    @Test
    public void testAdd(){
        Calc c = new Calc();
        Parser p = new Parser();
        double result = c.calculate(p.createTokenList("2+1"));
        Assert.assertEquals(result, 3, 0);
    }
    @Test
    public void testAddWithFloatingPoint(){
        Calc c = new Calc();
        Parser p = new Parser();
        double result = c.calculate(p.createTokenList("0+0.1"));
        Assert.assertEquals(result, 0.1, 0);
    }
    @Test
    public void subTest(){
        Calc c = new Calc();
        Parser p = new Parser();
        double result = c.calculate(p.createTokenList("0-1"));
        Assert.assertEquals(result, -1, 0);
        result = c.calculate(p.createTokenList("1-0"));
        Assert.assertEquals(result, 1, 0);
        result = c.calculate(p.createTokenList("1000000000-1000000000"));
        Assert.assertEquals(result, 0, 0);
    }
    @Test
    public void mulTest(){
        Calc c = new Calc();
        Parser p = new Parser();
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
        Calc c = new Calc();
        Parser p = new Parser();
        try {
            c.calculate(p.createTokenList("12/20/0"));
        } catch (Error e){
            Assert.assertEquals(Calc.CALC_ERROR_MESSAGE_DIV_0, e.getMessage());
        }
    }
}
