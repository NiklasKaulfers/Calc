package src;
import javax.swing.*;


import java.awt.GridLayout;
import java.awt.event.*;

/**
 * builds the User Interface 
 */
public class GUI implements ActionListener{
    private JButton clearButton;
    private JButton plusSign, minusSign, multiplySign, divideSign, solveSign
    , moduloSign, openBracket, closeBracket;
    private JButton num1, num2, num3, num4, num5, num6, num7, num8, num9, num0, dot;
    private JLabel display, inputs;
    private JFrame frame;
    private JPanel buttonPanel, outputPanel, mainPanel;

    private Parser parser;
    private Calc calc;

    private String inputString, displayString;


    /**
     * creates the default version of the GUI and instantiates it
     */
    public GUI(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new JPanel(new GridLayout(2,1));
    
        parser = new Parser();
        calc = new Calc();
        
        outputPanel = new JPanel(new GridLayout(2,1));
        displayString = "0";
        inputString = "";
        display = new JLabel(displayString);
        inputs = new JLabel("");
        outputPanel.add(inputs);
        outputPanel.add(display);
        mainPanel.add(outputPanel);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5,4));

        addButton(buttonPanel, clearButton = new JButton("CE"));
        addButton(buttonPanel, openBracket = new JButton("("));
        addButton(buttonPanel, closeBracket = new JButton(")"));
        addButton(buttonPanel, moduloSign = new JButton("%"));

        addButton(buttonPanel, num7 = new JButton("7"));
        addButton(buttonPanel, num8 = new JButton("8"));
        addButton(buttonPanel, num9 = new JButton("9"));
        addButton(buttonPanel, divideSign = new JButton("DIV"));
        
        addButton(buttonPanel, num4 = new JButton("4"));
        addButton(buttonPanel, num5 = new JButton("5"));
        addButton(buttonPanel, num6 = new JButton("6"));
        addButton(buttonPanel, multiplySign = new JButton("MUL"));

        addButton(buttonPanel, num1 = new JButton("1"));
        addButton(buttonPanel, num2 = new JButton("2"));
        addButton(buttonPanel, num3 = new JButton("3"));
        addButton(buttonPanel, minusSign = new JButton("MIN"));
        
        addButton(buttonPanel, dot = new JButton("DOT"));
        addButton(buttonPanel, num0 = new JButton("0"));
        solveSign = new JButton("=");
        solveSign.addActionListener(e -> {
            double res = calc.calculate(parser.createTokenList(inputString));
            display.setText(res + "");
        });
        buttonPanel.add(solveSign);
        addButton(buttonPanel, plusSign = new JButton("PLUS"));

        mainPanel.add(buttonPanel);

        frame.add(mainPanel);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * quick function to add a button to a panel
     * @param panel the panel to add to
     * @param button the button that will be added
     */
    private void addButton(JPanel panel, JButton button){
        button.addActionListener(this);
        panel.add(button);
    }

    /**
     * action events for the buttons, also updates the inputs display on each input
     */
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == num0){
            inputString += "0";
        }
        if (e.getSource() == num1){
            inputString += "1";
        }
        if (e.getSource() == num2){
            inputString += "2";
        }
        if (e.getSource() == num3){
            inputString += "3";
        }
        if (e.getSource() == num4){
            inputString += "4";
        }
        if (e.getSource() == num5){
            inputString += "5";
        }
        if (e.getSource() == num6){
            inputString += "6";
        }
        if (e.getSource() == num7){
            inputString += "7";
        }
        if (e.getSource() == num8){
            inputString += "8";
        }
        if (e.getSource() == num9){
            inputString += "9";
        }
        if (e.getSource() == dot){
            inputString += ".";
        }
        if (e.getSource() == multiplySign){
            inputString += "*";
        }
        if (e.getSource() == divideSign){
            inputString += "/";
        }
        if (e.getSource() == plusSign){
            inputString += "+";
        }
        if (e.getSource() == minusSign){
            inputString += "-";
        }
        if (e.getSource() == moduloSign){
            inputString += "%";
        }
        if (e.getSource() == openBracket){
            inputString += "(";
        }
        if (e.getSource() == closeBracket){
            inputString += ")";
        }
        // clears inputString
        if (e.getSource() == clearButton){
            inputString = "";
        }


        inputs.setText(inputString);
    }
}
