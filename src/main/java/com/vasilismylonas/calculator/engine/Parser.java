package com.vasilismylonas.calculator.engine;

public abstract class Parser {
  private final CalculatorEngine engine;

  public CalculatorEngine getEngine() { return engine; }

  public String tokenize(String expression) {
    for (var operator : getEngine().getOperators()) {
      expression = expression.replace(operator, " " + operator + " ");
    }

    return expression.replace("(", " ( ").replace(")", " ) ");
  }

  public Parser(CalculatorEngine engine) { this.engine = engine; }

  public abstract double parse(String expression) throws SyntaxException;
}
