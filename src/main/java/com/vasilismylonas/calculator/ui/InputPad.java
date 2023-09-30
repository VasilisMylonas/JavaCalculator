package com.vasilismylonas.calculator.ui;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class InputPad extends JPanel {
    private final CallbackActionListener inputListener = new CallbackActionListener();
    private final CallbackActionListener clearListener = new CallbackActionListener();
    private final CallbackActionListener clearEntryListener = new CallbackActionListener();
    private final CallbackActionListener enterListener = new CallbackActionListener();

    private static final String[][] basicSymbols = {
            { "  7  ", "  8  ", "  9  ", "  +  ", "  (  " },
            { "  4  ", "  5  ", "  6  ", "  -  ", "  )  " },
            { "  1  ", "  2  ", "  3  ", "  *  ", "  π  " },
            { "  0  ", "  .  ", "  ^  ", "  /  ", "  e  " },
    };

    private static final String[][] advancedSymbols = {
            { "sin", "cos", "tan", "sqrt", "abs" },
            { "sec", "csc", "cot", "cbrt", "sign" },
            { "asin", "acos", "atan", "log", "deg" },
            { "asec", "acsc", "acot", "exp", " ! " },
            { "floor", "ceil" },
    };

    public void onInput(ActionListener listener) {
        this.inputListener.setListener(listener);
    }

    public void onEnter(ActionListener listener) {
        this.enterListener.setListener(listener);
    }

    public void onClear(ActionListener listener) {
        this.clearListener.setListener(listener);
    }

    public void onClearEntry(ActionListener listener) {
        this.clearEntryListener.setListener(listener);
    }

    private final int BUTTON_GAP = 1;

    public InputPad() {
        super();

        setLayout(new GridBagLayout());
        var gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(BUTTON_GAP, BUTTON_GAP, BUTTON_GAP, BUTTON_GAP);

        JButton btn = new ControlButton("  C  ");
        btn.addActionListener(clearListener);
        add(btn, gbc);
        gbc.gridx++;

        btn = new ControlButton("  CE  ");
        btn.addActionListener(clearEntryListener);
        add(btn, gbc);
        gbc.gridx++;

        btn = new ControlButton("ADV");
        // btn.addActionListener(); TODO
        add(btn, gbc);
        gbc.gridx++;

        btn = new ControlButton("  =  ");
        btn.addActionListener(enterListener);
        gbc.gridwidth = 2;
        add(btn, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 1;
        gbc.gridy++;

        for (String[] row : basicSymbols) {
            for (String symbol : row) {
                btn = new InputButton(symbol);
                btn.addActionListener(inputListener);
                add(btn, gbc);
                gbc.gridx++;
            }
            gbc.gridx = 1;
            gbc.gridy++;
        }
    }
}
