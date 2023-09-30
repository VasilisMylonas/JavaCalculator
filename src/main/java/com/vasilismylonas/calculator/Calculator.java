package com.vasilismylonas.calculator;

import com.vasilismylonas.calculator.engine.CalculatorEngineImpl;
import com.vasilismylonas.calculator.engine.InfixParser;
import com.vasilismylonas.calculator.engine.Parser;
import com.vasilismylonas.calculator.engine.SyntaxException;
import com.vasilismylonas.calculator.ui.CalculatorView;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class Calculator {
    private final CalculatorView view;
    private final Parser parser;
    private final HashMap<Integer, Runnable> keyMap = new HashMap<>();

    private boolean handleKeyEvent(KeyEvent e) {
        int key = e.getKeyCode();
        if (e.getID() == KeyEvent.KEY_PRESSED && keyMap.containsKey(key)) {
            keyMap.get(key).run();
            return true;
        }
        return false;
    }

    public Calculator(CalculatorView view, Parser parser) {
        this.view = view;
        this.parser = parser;

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this::handleKeyEvent);

        view.onEnter(this::onEnter);
        view.onClear(this::onClear);
        view.onClearEntry(this::onClearEntry);
        view.onInput(this::onInput);
        keyMap.put(KeyEvent.VK_ENTER, this::onEnter);
        // TODO: problems with display
        keyMap.put(KeyEvent.VK_BACK_SPACE, this::onClearEntry);
    }

    private void onEnter() {
        var expr = view.getDisplayText();
        String text;

        try {
            var result = parser.parse(expr);
            text = String.format("%.8f", result);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            text = "UNKNOWN: " + e.getMessage();
        } catch (ArithmeticException e) {
            System.out.println(e);
            text = "ERROR";
        } catch (SyntaxException e) {
            System.out.println(e);
            text = "SYNTAX ERROR";
        }

        view.setDisplayText(text);
    }

    private void onClear() {
        view.setDisplayText("");
    }

    private void onClearEntry() {
        var text = view.getDisplayText();
        var len = text.length();
        if (len > 0) {
            view.setDisplayText(text.substring(0, text.length() - 1));
        }
    }

    private void onInput(String input) {
        view.setDisplayText(view.getDisplayText() + input);
    }

    public static void main(String[] args) {
        var engine = new CalculatorEngineImpl();
        var parser = new InfixParser(engine);
        var view = new CalculatorView();
        var presenter = new Calculator(view, parser);
        view.setVisible(true);
    }
}
