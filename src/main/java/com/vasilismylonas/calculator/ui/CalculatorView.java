package com.vasilismylonas.calculator.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;
import javax.swing.*;

public class CalculatorView extends JFrame {
    private final Display display = new Display();
    private final InputPad inputPad = new InputPad();

    public void setDisplayText(String text) {
        display.setText(text);
    }

    public String getDisplayText() {
        return display.getText();
    }

    public void onEnter(Runnable handler) {
        inputPad.onEnter(e -> handler.run());
    }

    public void onClear(Runnable handler) {
        inputPad.onClear(e -> handler.run());
    }

    public void onClearEntry(Runnable handler) {
        inputPad.onClearEntry(e -> handler.run());
    }

    private void inputReceived(ActionEvent e) {
        handler.accept(((InputButton) e.getSource()).getText().strip());
    }

    private Consumer<String> handler;

    public void onInput(Consumer<String> handler) {
        this.handler = handler;
        inputPad.onInput(this::inputReceived);
    }

    private final JPanel panel = new JPanel();

    public CalculatorView() {
        setTitle("Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400, 500));
        setResizable(false);

        var gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;

        panel.setLayout(new GridBagLayout());

        gbc.gridy = 1;
        gbc.weighty = 2;
        panel.add(display, gbc);

        gbc.gridy = 3;
        gbc.weighty = 17;
        panel.add(inputPad, gbc);

        add(panel);
    }
}
