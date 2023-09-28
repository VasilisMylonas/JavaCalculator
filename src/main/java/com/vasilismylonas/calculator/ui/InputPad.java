package com.vasilismylonas.calculator.ui;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class InputPad extends JPanel {
  private final CallbackActionListener listener = new CallbackActionListener();

  public void onInput(ActionListener listener) {
    this.listener.setListener(listener);
  }

  public InputPad(String[][] symbols) {
    super();

    setLayout(new GridBagLayout());

    var gbc = new MyGridBagConstraints();

    for (String[] row : symbols) {
      for (String symbol : row) {
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
