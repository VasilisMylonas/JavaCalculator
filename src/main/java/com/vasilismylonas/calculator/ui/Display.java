package com.vasilismylonas.calculator.ui;

import java.awt.*;
import javax.swing.*;

public class Display extends JTextField {
  public Display() {
    super("");
    var font = getFont().deriveFont(Font.PLAIN, 20);
    setFont(font);
  }
}
