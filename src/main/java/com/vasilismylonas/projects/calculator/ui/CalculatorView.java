package com.vasilismylonas.projects.calculator.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CalculatorView extends JFrame {
    private final Numpad numpad;
    private final JTextField display;
    private final JButton enterButton, backspaceButton;
    public void setDisplayText(String text) {
        display.setText(text);
    }
    public String getDisplayText() {
        return display.getText();
    }
    public void addNumpadListener(ActionListener listener) {
        numpad.addActionListener(listener);
    }
    public void addBackspaceListener(ActionListener listener) { backspaceButton.addActionListener(listener); }
    public void addEnterListener(ActionListener listener) {
        enterButton.addActionListener(listener);
    }

    public CalculatorView() {
        setTitle("Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400, 500));

        display = new JTextField("");
        display.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        numpad = new Numpad();
        enterButton = CalculatorButton.createRedButton("ENTER");
        backspaceButton = CalculatorButton.createRedButton("BACKSPACE");

        var gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;

        var p = new JPanel();
        p.setLayout(new GridBagLayout());

        gbc.gridx = 1;
        gbc.gridy = 1;
        p.add(backspaceButton, gbc);
        gbc.gridx++;
        p.add(enterButton, gbc);

        setLayout(new GridBagLayout());

        gbc.gridx = 1;
        gbc.gridy = 1;

        add(display, gbc);
        gbc.gridy++;
        gbc.weighty = 1;
        add(p, gbc);
        gbc.weighty = 8;
        gbc.gridy++;
        add(numpad, gbc);
    }
}
