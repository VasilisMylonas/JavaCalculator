package com.vasilismylonas.projects.calculator;

import com.vasilismylonas.projects.calculator.engine.CalculatorEngine;
import com.vasilismylonas.projects.calculator.engine.InfixParser;
import com.vasilismylonas.projects.calculator.engine.Parser;

public class CalculatorModel {
    private Parser parser;

    public CalculatorModel(CalculatorEngine engine) {
        parser = new InfixParser(engine);
    }

    private String tokenize(String expression) {
        for (var operator: parser.getEngine().getOperators()) {
            expression = expression.replace(operator, " " + operator + " ");
        }

        return expression.replace("(", " ( ")
                .replace(")", " ) ");
    }

    public double eval(String expression) {
        return getParser().parse(tokenize(expression));
    }

    public Parser getParser() {
        return parser;
    }
}
