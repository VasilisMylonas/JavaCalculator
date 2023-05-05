package com.vasilismylonas.projects.calculator;

import com.vasilismylonas.projects.calculator.engine.CalculatorEngineImpl;
import com.vasilismylonas.projects.calculator.ui.CalculatorButton;
import com.vasilismylonas.projects.calculator.ui.CalculatorView;

import java.awt.event.ActionEvent;

public class CalculatorPresenter {
    private CalculatorView view;
    private CalculatorModel model;

    public static void main(String[] args) {
        var engine = new CalculatorEngineImpl();
        var view = new CalculatorView();
        var model = new CalculatorModel(engine);
        var presenter = new CalculatorPresenter(view, model);
        view.setVisible(true);
    }

    public CalculatorPresenter(CalculatorView view, CalculatorModel model) {
        this.view = view;
        this.model = model;
        view.addEnterListener(this::onEnter);
        view.addNumpadListener(this::onNumpadClick);
        view.addBackspaceListener(this::onBackspaceClick);
    }

    public void onEnter(ActionEvent e) {
        var expr = view.getDisplayText();
        var result = model.eval(expr);
        view.setDisplayText(String.valueOf(result));
    }

    public void onNumpadClick(ActionEvent e) {
        var newText = view.getDisplayText() + ((CalculatorButton)e.getSource()).getText();
        view.setDisplayText(newText);
    }

    public void onBackspaceClick(ActionEvent e) {
        var text = view.getDisplayText();

        if (text.length() > 0) {
            view.setDisplayText(text.substring(0, text.length() - 1));
        }
    }

}
