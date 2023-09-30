package com.vasilismylonas.calculator.engine;

import java.util.Collection;

public interface CalculatorEngine {
  double getNumber(String name);

  int getPriority(String operator);

  boolean isAssociative(String operator);

  boolean isFunction(String token);

  boolean isOperator(String token);

  boolean isNumber(String token);

  double evalFunction(String function, double arg);

  double evalOperator(String operator, double a, double b);

  Collection<String> getOperators();

  Collection<String> getFunctions();
}
