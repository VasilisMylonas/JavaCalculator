package com.vasilismylonas.projects.calculator.engine;

public interface Parser {
    double parse(String expression);
    CalculatorEngine getEngine();
}
