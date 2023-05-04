package com.vasilismylonas.projects.calculator.ui;

import javax.swing.*;
import java.awt.*;

public class CalculatorButton extends JButton {
    private static final Color DEFAULT_COLOR = Color.WHITE;
    private static final Color HOVER_COLOR = new Color(238, 238, 238);
    private static final Color CLICK_COLOR = new Color(210, 210, 210);

    public CalculatorButton(String text) {
        super(text);

        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder());

        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);

        addChangeListener(evt -> {
            if (getModel().isPressed()) {
                setBackground(CLICK_COLOR);
            } else if (getModel().isRollover()) {
                setBackground(HOVER_COLOR);
            } else {
                setBackground(DEFAULT_COLOR);
            }
        });
    }
}
