package com.vasilismylonas.calculator.ui;

import java.awt.*;
import javax.swing.*;

public class CalculatorButton extends JButton {
  public CalculatorButton(String text, Color defaultColor, Color hoverColor,
                          Color clickColor) {
    super(text);

    var font = getFont().deriveFont(Font.PLAIN, 20);
    setFont(font);

    setBackground(defaultColor);
    setBorder(BorderFactory.createEmptyBorder());

    setFocusPainted(false);
    setContentAreaFilled(false);
    setOpaque(true);

    addChangeListener(evt -> {
      if (getModel().isPressed()) {
        setBackground(clickColor);
      } else if (getModel().isRollover()) {
        setBackground(hoverColor);
      } else {
        setBackground(defaultColor);
      }
    });
  }
}
