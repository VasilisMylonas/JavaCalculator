package com.vasilismylonas.calculator.ui;

import java.awt.*;

public class InputButton extends CalculatorButton {
  public InputButton(String text) {
    super(text, Color.WHITE, new Color(238, 238, 238),
          new Color(210, 210, 210));

    setForeground(Color.BLACK);
  }
}
