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

    public void onBackspace(Runnable handler) {
        // controlPad.onBackspace(e -> handler.run());
    }

    public void onEnter(Runnable handler) {
        // controlPad.onEnter(e -> handler.run());
    }

    public void onClear(Runnable handler) {
        // controlPad.onClear(e -> handler.run());
    }

    public void onClearEntry(Runnable handler) {
        // controlPad.onClearEntry(e -> handler.run());
    }

    private void inputReceived(ActionEvent e) {
        handler.accept(((InputButton) e.getSource()).getText());
    }

    private Consumer<String> handler;

    public void onInput(Consumer<String> handler) {
        this.handler = handler;
        // basicPad.onInput(callback);
        // advancedPad.onInput(callback);
    }

    // private static final String BASIC = "BSC";

    // private static final String ADVANCED = "ADV";

    // private void onMode(ActionEvent e) {
    // var btn = ((ControlButton) e.getSource());
    // var layout = ((CardLayout) panel.getLayout());
    // if (btn.getText().equals(ADVANCED)) {
    // btn.setText(BASIC);
    // layout.show(panel, BASIC);
    // } else {
    // btn.setText(ADVANCED);
    // layout.show(panel, ADVANCED);
    // }
    // }

    private final JPanel panel = new JPanel();

    public CalculatorView() {
        setTitle("Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400, 500));

        var gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;

        panel.setLayout(new GridBagLayout());

        gbc.gridy = 1;
        gbc.weighty = 2;
        panel.add(display, gbc);

        // gbc.insets = new Insets(0, 2, 2, 2);
        gbc.gridy = 2;
        gbc.weighty = 8;
        panel.add(inputPad, gbc);

        add(panel);
    }
}
