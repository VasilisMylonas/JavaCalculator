package com.vasilismylonas.projects.calculator.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CallbackActionListener  implements ActionListener {
    private ActionListener listener;

    public void setListener(ActionListener listener) {
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (listener != null) {
            listener.actionPerformed(e);
        }
    }
}
