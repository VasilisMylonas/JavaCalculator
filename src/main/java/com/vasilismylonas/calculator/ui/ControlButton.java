package com.vasilismylonas.calculator.ui;

import java.awt.*;

public class ControlButton extends CalculatorButton {
  public ControlButton(String text) {
    super(text, new Color(255, 104, 104), new Color(232, 90, 90),
          new Color(206, 50, 50));

    setForeground(Color.WHITE);
  }
}
