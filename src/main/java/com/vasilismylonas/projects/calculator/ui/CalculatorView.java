package com.vasilismylonas.projects.calculator.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorView extends JFrame {
    private final InputPad inputPad = new InputPad();
    private final ControlPad controlPad = new ControlPad();
    private final Display display = new Display();

    public void setDisplayText(String text) {
        display.setText(text);
    }
    public String getDisplayText() {
        return display.getText();
    }

    private class InputPadActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            var newText = getDisplayText() + ((InputButton)e.getSource()).getText();
            setDisplayText(newText);
        }
    }

    public void onBackspace(ActionListener listener) { controlPad.onBackspace(listener); }

    public void onEnter(ActionListener listener) { controlPad.onEnter(listener); }

    public CalculatorView() {
        setTitle("Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400, 500));

        inputPad.onInput(new InputPadActionListener());
        setLayout(new GridBagLayout());

        var gbc = new MyGridBagConstraints();
        add(display, gbc);
        gbc.gridy++;
        gbc.weighty = 1;
        add(controlPad, gbc);
        gbc.weighty = 8;
        gbc.gridy++;
        add(inputPad, gbc);
    }
}
