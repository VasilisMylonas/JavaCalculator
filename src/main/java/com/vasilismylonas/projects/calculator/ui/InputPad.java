package com.vasilismylonas.projects.calculator.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InputPad extends JPanel {
    private static final String[][] symbols = {
            {"7", "8", "9", "+"},
            {"4", "5", "6", "-"},
            {"1", "2", "3", "*"},
            {"0", ".", "/"},
    };

    private final CallbackActionListener listener = new CallbackActionListener();

    public void onInput(ActionListener listener) {
        this.listener.setListener(listener);
    }

    public InputPad() {
        super();

        setLayout(new GridBagLayout());

        var gbc = new MyGridBagConstraints();

        for (String[] row : symbols) {
            for (String symbol : row) {
                gbc.gridwidth = 1;

                if (symbol.equals("0")) {
                    gbc.gridwidth = 2;
                }

                var btn = new InputButton(symbol);
                btn.addActionListener(listener);
                add(btn, gbc);
                gbc.gridx += gbc.gridwidth;
            }

            gbc.gridx = 1;
            gbc.gridy++;
        }
    }
}
