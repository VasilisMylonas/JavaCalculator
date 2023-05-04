package com.vasilismylonas.projects.calculator;

import com.vasilismylonas.projects.calculator.ui.CalculatorView;

public class Main {
    public static void main(String[] args) {
        var engine = new CalculatorEngineImpl();
        var view = new CalculatorView(engine.getFunctions());
        var model = new CalculatorModel(engine);
        var presenter = new CalculatorPresenter(view, model);
        view.setVisible(true);
    }
}