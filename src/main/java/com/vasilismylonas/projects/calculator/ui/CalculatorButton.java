package com.vasilismylonas.projects.calculator.ui;

import javax.swing.*;
import java.awt.*;

public class CalculatorButton extends JButton {
    public static CalculatorButton createWhiteButton(String text) {
        return new CalculatorButton(text,
                Color.WHITE,
                new Color(238, 238, 238),
                new Color(210, 210, 210)
        );
    }

    public static CalculatorButton createRedButton(String text) {
        var btn = new CalculatorButton(text,
                new Color(255, 104, 104),
                new Color(232, 90, 90),
                new Color(206, 50, 50)
        );

        btn.setForeground(Color.WHITE);
        return btn;
    }

    public CalculatorButton(String text, Color defaultColor, Color hoverColor, Color clickColor) {
        super(text);

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
