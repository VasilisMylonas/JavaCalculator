package com.vasilismylonas.projects.calculator.ui;

import javax.swing.*;
import java.awt.*;

public class Display extends JTextField {
    public Display() {
        super("");
        var font = getFont().deriveFont(Font.PLAIN, 20);
        setFont(font);
    }
}
