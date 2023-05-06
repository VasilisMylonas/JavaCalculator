package com.vasilismylonas.projects.calculator.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ControlPad extends JPanel {
    private final ControlButton enterButton =  new ControlButton("ENTER");
    private final ControlButton backspaceButton = new ControlButton("BACKSPACE");
    private final CallbackActionListener enterListener = new CallbackActionListener();
    private final CallbackActionListener backspaceListener = new CallbackActionListener();

    public void onEnter(ActionListener listener) { enterListener.setListener(listener); }

    public void onBackspace(ActionListener listener) { backspaceListener.setListener(listener); }

    public ControlPad() {
        super();

        setLayout(new GridBagLayout());

        var gbc = new MyGridBagConstraints();

        add(backspaceButton, gbc);
        backspaceButton.addActionListener(backspaceListener);

        gbc.gridx++;

        add(enterButton, gbc);
        enterButton.addActionListener(enterListener);
    }
}
