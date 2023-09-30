package com.vasilismylonas.calculator.ui;

import java.awt.*;
import javax.swing.*;

public class Display extends JTextField {
  public Display() {
    super("");
    var font = getFont().deriveFont(Font.PLAIN, 32);
    setFont(font);
    setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, false));
  }
}
