package com.vasilismylonas.projects.calculator;

import com.vasilismylonas.projects.calculator.engine.CalculatorEngineImpl;
import com.vasilismylonas.projects.calculator.engine.InfixParser;
import com.vasilismylonas.projects.calculator.engine.Parser;
import com.vasilismylonas.projects.calculator.ui.CalculatorView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class CalculatorGui {
    private final CalculatorView view;
    private final Parser parser;
    private final HashMap<Integer, Runnable> keyMap = new HashMap<>();

    private String tokenize(String expression) {
        for (var operator: parser.getEngine().getOperators()) {
            expression = expression.replace(operator, " " + operator + " ");
        }

        return expression.replace("(", " ( ")
                .replace(")", " ) ");
    }

    private boolean handleKeyEvent(KeyEvent e) {
        int key = e.getKeyCode();
        if (e.getID() == KeyEvent.KEY_PRESSED && keyMap.containsKey(key)) {
            keyMap.get(key).run();
            return true;
        }
        return false;
    }

    public CalculatorGui(CalculatorView view, Parser parser) {
        this.view = view;
        this.parser = parser;

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this::handleKeyEvent);

        view.onEnter(this::onEnter);
        view.onBackspace(this::onBackspace);
        view.onClear(this::onClear);
        view.onClearEntry(this::onClearEntry);
        view.onInput(this::onInput);
        keyMap.put(KeyEvent.VK_ENTER, this::onEnter);
        // TODO: problems with display
//        keyMap.put(KeyEvent.VK_BACK_SPACE, this::onBackspace);
    }

    private void onEnter() {
        var expr = view.getDisplayText();
        String text;

        try {
            var result = parser.parse(tokenize(expr));
            text = String.format("%.8f", result);
        } catch (Exception e) {
            text = e.getMessage();
        }

        view.setDisplayText(text);
    }

    private void onBackspace() {
        var text = view.getDisplayText();

        if (text.length() > 0) {
            view.setDisplayText(text.substring(0, text.length() - 1));
        }
    }

    private void onClear()  {
        view.setDisplayText("");
    }

    private void onClearEntry()  {
        // TODO
        System.out.println("CLEAR ENTRY NOT IMPLEMENTED!");
    }

    private void onInput(String input) {
        view.setDisplayText(view.getDisplayText() + input);
    }

    public static void main(String[] args) {
        var engine = new CalculatorEngineImpl();
        var parser = new InfixParser(engine);
        var view = new CalculatorView();
        var presenter = new CalculatorGui(view, parser);
        view.setVisible(true);
    }

}
