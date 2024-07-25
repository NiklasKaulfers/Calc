package src;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

/**
 * builds the User Interface 
 */

public class GUI implements ActionListener{
    private JButton plusSign, minusSign, multiplySign, divideSign, solveSign, clearButton;
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
        mainPanel = new JPanel();
    
        parser = new Parser();
        calc = new Calc();
        
        outputPanel = new JPanel();
        displayString = "0";
        inputString = "";
        display = new JLabel(displayString);
        inputs = new JLabel("0");
        outputPanel.add(inputs);
        outputPanel.add(display);
        mainPanel.add(outputPanel, BorderLayout.NORTH);


        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,4));
        num7 = new JButton("7");
        num7.addActionListener(this);
        buttonPanel.add(num7);
        num8 = new JButton("8");
        num8.addActionListener(this);
        buttonPanel.add(num8);
        num9 = new JButton("9");
        num9.addActionListener(this);
        buttonPanel.add(num9);
        divideSign = new JButton("DIV");
        divideSign.addActionListener(this);
        buttonPanel.add(divideSign);
        num4 = new JButton("4");
        num4.addActionListener(this);
        buttonPanel.add(num4);
        num5 = new JButton("5");
        num5.addActionListener(this);
        buttonPanel.add(num5);
        num6 = new JButton("6");
        num6.addActionListener(this);
        buttonPanel.add(num6);
        multiplySign = new JButton("MUL");
        multiplySign.addActionListener(this);
        buttonPanel.add(multiplySign);
        num1 = new JButton("1");
        num1.addActionListener(this);
        buttonPanel.add(num1);
        num2 = new JButton("2");
        num2.addActionListener(this);
        buttonPanel.add(num2);
        num3 = new JButton("3");
        num3.addActionListener(this);
        buttonPanel.add(num3);
        minusSign = new JButton("MIN");
        minusSign.addActionListener(this);
        buttonPanel.add(minusSign);
        dot = new JButton("DOT");
        // TODO: function in parser and calc for this
        dot.addActionListener(e -> {

        });
        buttonPanel.add(dot);
        num0 = new JButton("0");
        num0.addActionListener(this);
        buttonPanel.add(num0);
        solveSign = new JButton("=");
        solveSign.addActionListener(e -> {
            double res = calc.calculate(parser.createTokenList(inputString));
            display.setText(res + "");
        });
        buttonPanel.add(solveSign);
        plusSign = new JButton("PLUS");
        plusSign.addActionListener(this);
        buttonPanel.add(plusSign);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        clearButton = new JButton("ce");
        clearButton.addActionListener(this);
        mainPanel.add(clearButton);

        frame.add(mainPanel);


        frame.pack();
        frame.setVisible(true);
    }

    /**
     * action events for the buttons, also updates the inputs display on each input
     */
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == num0){
            inputString = inputString + "0";
        }
        if (e.getSource() == num1){
            inputString = inputString + "1";
        }
        if (e.getSource() == num2){
            inputString = inputString + "2";
        }
        if (e.getSource() == num3){
            inputString = inputString + "3";
        }
        if (e.getSource() == num4){
            inputString = inputString + "4";
        }
        if (e.getSource() == num5){
            inputString = inputString + "5";
        }
        if (e.getSource() == num6){
            inputString = inputString + "6";
        }
        if (e.getSource() == num7){
            inputString = inputString + "7";
        }
        if (e.getSource() == num8){
            inputString = inputString + "8";
        }
        if (e.getSource() == num9){
            inputString = inputString + "9";
        }
        if (e.getSource() == multiplySign){
            inputString = inputString + "*";
        }
        if (e.getSource() == divideSign){
            inputString = inputString + "/";
        }
        if (e.getSource() == plusSign){
            inputString = inputString + "+";
        }
        if (e.getSource() == minusSign){
            inputString = inputString + "-";
        }
        if (e.getSource() == clearButton){
            inputString = "";
        }
        inputs.setText(inputString);
    }

}
