package com.vasilismylonas.projects.calculator;

import com.vasilismylonas.projects.calculator.engine.CalculatorEngineImpl;
import com.vasilismylonas.projects.calculator.ui.CalculatorView;

import java.awt.event.ActionEvent;

public class CalculatorPresenter {
    private final CalculatorView view;
    private final CalculatorModel model;

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
        view.onEnter(this::onEnter);
        view.onBackspace(this::onBackspace);
    }

    public void onEnter(ActionEvent e) {
        var expr = view.getDisplayText();
        var result = model.eval(expr);
        view.setDisplayText(String.valueOf(result));
    }

    public void onBackspace(ActionEvent e) {
        var text = view.getDisplayText();

        if (text.length() > 0) {
            view.setDisplayText(text.substring(0, text.length() - 1));
        }
    }

}
