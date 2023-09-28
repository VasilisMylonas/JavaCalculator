package com.vasilismylonas.calculator.ui;

import java.awt.*;

public class MyGridBagConstraints extends GridBagConstraints {
  public MyGridBagConstraints() {
    super();
    insets = new Insets(2, 2, 2, 2);
    weightx = 1;
    weighty = 1;
    fill = GridBagConstraints.BOTH;
    gridx = 1;
    gridy = 1;
  }
}
