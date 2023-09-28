package com.vasilismylonas.calculator.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
import javax.swing.*;

public class CalculatorView extends JFrame {
  private static final String[][] basicSymbols = {
      {"7", "8", "9", "+", "("},
      {"4", "5", "6", "-", ")"},
      {"1", "2", "3", "*", "π"},
      {"0", ".", "^", "/", "e"},
  };

  private static final String[][] advancedSymbols = {
      {"sin", "cos", "tan", "sqrt", "abs"},
      {"sec", "csc", "cot", "cbrt", "sign"},
      {"arcsin", "arccos", "arctan", "log", "deg"},
      {"arcsec", "arccsc", "arccot", "exp", "fact"},
      {"floor", "ceil"}};

  private final InputPad basicPad = new InputPad(basicSymbols);
  private final InputPad advancedPad = new InputPad(advancedSymbols);
  private final ControlPad controlPad = new ControlPad();
  private final Display display = new Display();

  public void setDisplayText(String text) { display.setText(text); }

  public String getDisplayText() { return display.getText(); }

  public void onBackspace(Runnable handler) {
    controlPad.onBackspace(e -> handler.run());
  }

  public void onEnter(Runnable handler) {
    controlPad.onEnter(e -> handler.run());
  }

  public void onClear(Runnable handler) {
    controlPad.onClear(e -> handler.run());
  }

  public void onClearEntry(Runnable handler) {
    controlPad.onClearEntry(e -> handler.run());
  }

  public void onInput(Consumer<String> handler) {
    var callback = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        handler.accept(((InputButton)e.getSource()).getText());
      }
    };

    basicPad.onInput(callback);
    advancedPad.onInput(callback);
  }

  private static final String BASIC = "BSC";

  private static final String ADVANCED = "ADV";

  private void onMode(ActionEvent e) {
    var btn = ((ControlButton)e.getSource());
    var layout = ((CardLayout)panel.getLayout());
    if (btn.getText().equals(ADVANCED)) {
      btn.setText(BASIC);
      layout.show(panel, BASIC);
    } else {
      btn.setText(ADVANCED);
      layout.show(panel, ADVANCED);
    }
  }

  private final JPanel panel = new JPanel();

  public CalculatorView() {
    setTitle("Calculator");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setMinimumSize(new Dimension(400, 500));
    setLayout(new GridBagLayout());

    panel.setLayout(new CardLayout());
    panel.add(basicPad, BASIC);
    panel.add(advancedPad, ADVANCED);

    controlPad.onMode(this::onMode);
    controlPad.setMode(BASIC);

    var gbc = new MyGridBagConstraints();
    add(display, gbc);
    gbc.gridy++;
    gbc.weighty = 1;
    add(controlPad, gbc);
    gbc.weighty = 8;
    gbc.gridy++;
    add(panel, gbc);
  }
}
