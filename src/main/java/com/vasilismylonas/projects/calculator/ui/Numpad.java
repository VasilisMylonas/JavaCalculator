package com.vasilismylonas.projects.calculator.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Numpad extends JPanel {
    private static String[][] symbols = {
            {"7", "8", "9", "+"},
            {"4", "5", "6", "-"},
            {"1", "2", "3", "*"},
    };
    private ArrayList<ActionListener> listeners = new ArrayList<>();
    private void addButton(String symbol, GridBagConstraints gbc) {
        var btn = CalculatorButton.createWhiteButton(symbol);
        btn.addActionListener(e -> {
            for (var listener : listeners) {
                listener.actionPerformed(e);
            }
        });
        add(btn, gbc);
    }
    public void addActionListener(ActionListener listener) {
        listeners.add(listener);
    }

    public Numpad() {
        setLayout(new GridBagLayout());

        var gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;

        for (String[] row : symbols) {
            for (String digit : row) {
                addButton(digit, gbc);
                gbc.gridx++;
            }
            gbc.gridx = 1;
            gbc.gridy++;
        }

        gbc.gridwidth = 2;
        addButton("0", gbc);

        gbc.gridx = 3;
        gbc.gridwidth = 1;
        addButton(".", gbc);

        gbc.gridx = 4;
        gbc.gridwidth = 1;
        addButton("/", gbc);
    }
}
