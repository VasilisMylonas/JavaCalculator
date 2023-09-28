package com.vasilismylonas.calculator.ui;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class InputPad extends JPanel {
    private final CallbackActionListener listener = new CallbackActionListener();

    private static final String[][] basicSymbols = {
            { "7", "8", "9", "+", "(" },
            { "4", "5", "6", "-", ")" },
            { "1", "2", "3", "*", "π" },
            { "0", ".", "^", "/", "e" },
    };

    private static final String[][] advancedSymbols = {
            { "sin", "cos", "tan", "sqrt", "abs" },
            { "sec", "csc", "cot", "cbrt", "sign" },
            { "arcsin", "arccos", "arctan", "log", "deg" },
            { "arcsec", "arccsc", "arccot", "exp", "fact" },
            { "floor", "ceil" },
    };

    public void onInput(ActionListener listener) {
        this.listener.setListener(listener);
    }

    public InputPad() {
        super();

        setLayout(new GridLayout(4, 4, 0, 0));

        for (String[] row : basicSymbols) {
            for (String symbol : row) {
                add(new InputButton(symbol));
            }
        }

        setLayout(new GridBagLayout());
    }
}
