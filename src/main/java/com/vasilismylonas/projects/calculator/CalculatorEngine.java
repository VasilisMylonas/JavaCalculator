package com.vasilismylonas.projects.calculator;

import java.util.Collection;
import java.util.function.Function;

public interface CalculatorEngine {
    int getPriority(String operator);
    boolean isAssociative(String operator);
    boolean isFunction(String token);
    boolean isOperator(String token);
    boolean isNumber(String token);
    Function<Double, Double> getFunction(String name);

    Collection<String> getOperators();
    Collection<String> getFunctions();
}
