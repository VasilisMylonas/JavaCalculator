package com.vasilismylonas.calculator.ui;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ControlPad extends JPanel {
  private final ControlButton enterButton = new ControlButton("=");
  private final ControlButton backspaceButton = new ControlButton("BACKSPACE");
  private final ControlButton ceButton = new ControlButton("CE");
  private final ControlButton clearButton = new ControlButton("C");
  private final ControlButton modeButton = new ControlButton("");
  private final CallbackActionListener enterListener =
      new CallbackActionListener();
  private final CallbackActionListener backspaceListener =
      new CallbackActionListener();
  private final CallbackActionListener clearListener =
      new CallbackActionListener();
  private final CallbackActionListener ceListener =
      new CallbackActionListener();
  private final CallbackActionListener modeListener =
      new CallbackActionListener();

  public void onEnter(ActionListener listener) {
    enterListener.setListener(listener);
  }

  public void onBackspace(ActionListener listener) {
    backspaceListener.setListener(listener);
  }

  public void onClear(ActionListener listener) {
    clearListener.setListener(listener);
  }

  public void onClearEntry(ActionListener listener) {
    ceListener.setListener(listener);
  }

  public void onMode(ActionListener listener) {
    modeListener.setListener(listener);
  }

  private void addButton(ControlButton button, ActionListener actionListener,
                         GridBagConstraints gbc) {
    add(button, gbc);
    button.addActionListener(actionListener);
    gbc.gridx++;
  }

  public void setMode(String mode) { modeButton.setText(mode); }

  public ControlPad() {
    super();

    setLayout(new GridBagLayout());

    var gbc = new MyGridBagConstraints();

    gbc.weightx = 1;
    addButton(backspaceButton, backspaceListener, gbc);
    addButton(ceButton, ceListener, gbc);
    addButton(clearButton, clearListener, gbc);
    add(Box.createHorizontalGlue());
    addButton(enterButton, enterListener, gbc);
    addButton(modeButton, modeListener, gbc);
  }
}
