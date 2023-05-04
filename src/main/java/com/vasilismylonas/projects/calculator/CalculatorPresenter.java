package com.vasilismylonas.projects.calculator;

import com.vasilismylonas.projects.calculator.ui.CalculatorButton;
import com.vasilismylonas.projects.calculator.ui.CalculatorView;

import java.awt.event.ActionEvent;

public class CalculatorPresenter {
    private CalculatorView view;
    private CalculatorModel model;

    public CalculatorPresenter(CalculatorView view, CalculatorModel model) {
        this.view = view;
        this.model = model;
        view.addEnterListener(this::onEnter);
        view.addNumpadListener(this::onNumpadClick);
        view.addFunctionListener(this::onFunctionClick);
        view.addBackspaceListener(this::onBackspaceClick);
        view.addParserModeListener(this::onParserModeSwitch);
        view.setParserMode(model.getParserMode().toString());
    }

    public void onEnter(ActionEvent e) {
        var expr = view.getDisplayText();
        var result = model.eval(expr);
        view.setDisplayText(String.valueOf(result));
    }

    public void onParserModeSwitch(ActionEvent e) {
        var parserMode = view.getParserMode();
        if (parserMode == "INFIX") {
            view.setParserMode("POSTFIX");
            model.setParserMode(ParserMode.POSTFIX);
        } else {
            view.setParserMode("INFIX");
            model.setParserMode(ParserMode.INFIX);
        }
    }

    public void onNumpadClick(ActionEvent e) {
        var newText = view.getDisplayText() + ((CalculatorButton)e.getSource()).getText();
        view.setDisplayText(newText);
    }

    public void onFunctionClick(ActionEvent e) {
        var newText =  view.getDisplayText() + ((CalculatorButton)e.getSource()).getText() + "(";
        view.setDisplayText(newText);
    }

    public void onBackspaceClick(ActionEvent e) {
        var text = view.getDisplayText();

        if (text.length() > 0) {
            view.setDisplayText(text.substring(0, text.length() - 1));
        }
    }

}
