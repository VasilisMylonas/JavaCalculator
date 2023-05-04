package com.vasilismylonas.projects.calculator.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CalculatorView extends JFrame {
    private final Numpad numpad;
    private final FunctionPad functionPad;
    private final JTextField display;
    private final JButton enterButton, backspaceButton, parserModeButton;
    public void setDisplayText(String text) {
        display.setText(text);
    }
    public String getDisplayText() {
        return display.getText();
    }
    public void addNumpadListener(ActionListener listener) {
        numpad.addActionListener(listener);
    }
    public void addFunctionListener(ActionListener listener) {
        functionPad.addActionListener(listener);
    }
    public void addBackspaceListener(ActionListener listener) { backspaceButton.addActionListener(listener); }
    public void addEnterListener(ActionListener listener) {
        enterButton.addActionListener(listener);
    }
    public void addParserModeListener(ActionListener listener) {
        parserModeButton.addActionListener(listener);
    }

    public void setParserMode(String mode) {
        parserModeButton.setText(mode);
    }

    public String getParserMode() {
        return parserModeButton.getText();
    }

    public CalculatorView(Collection<String> functions) {
        setTitle("Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 600));

        display = new JTextField("");
        display.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        numpad = new Numpad();
        enterButton = new CalculatorButton("ENTER");
        backspaceButton = new CalculatorButton("BACKSPACE");
        parserModeButton = new CalculatorButton("");
        functionPad = new FunctionPad(functions);

        var p = new JPanel();
        p.setLayout(new GridLayout(1, 3));
        p.add(backspaceButton);
        p.add(enterButton);

        setLayout(new GridLayout(2, 2));
        getContentPane().add(display);
        getContentPane().add(p);
        getContentPane().add(numpad);
        getContentPane().add(functionPad);
    }
}
