package com.vasilismylonas.projects.calculator.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

public class FunctionPad extends JPanel {
    private final ArrayList<ActionListener> listeners = new ArrayList<>();

    public void addActionListener(ActionListener listener) {
        listeners.add(listener);
    }

    private void addButton(String symbol, GridBagConstraints gbc) {
        var btn = new InputButton(symbol);
        btn.addActionListener(e -> {
            for (var listener : listeners) {
                listener.actionPerformed(e);
            }
        });
        add(btn, gbc);
    }

    public FunctionPad(Collection<String> functions) {
        var n = (int)Math.ceil(Math.sqrt(functions.size()));
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

        for (var fn : functions) {
            addButton(fn, gbc);
            gbc.gridx++;
            gbc.gridx %= 4;
            if (gbc.gridx == 1) {
                gbc.gridy++;
            }
        }
    }
}
