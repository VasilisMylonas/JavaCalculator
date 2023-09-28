package com.vasilismylonas.calculator.engine;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

public class PostfixParser extends Parser {
  private final Stack<Double> stack = new Stack<>();

  public PostfixParser(CalculatorEngine engine) { super(engine); }

  private void processToken(String token) throws SyntaxException {
    if (getEngine().isNumber(token)) {
      stack.push(getEngine().getNumber(token));
    } else if (getEngine().isFunction(token)) {
      var arg = stack.pop();
      stack.push(getEngine().evalFunction(token, arg));
    } else if (getEngine().isOperator(token)) {
      // TODO: expressions like 5 * (-5) or () will throw EmptyStackException
      // here
      var a = stack.pop();
      var b = stack.pop();
      stack.push(getEngine().evalOperator(token, b, a));
    } else {
      throw new SyntaxException("Unexpected " + token);
    }
  }

  public double parse(String expression) throws SyntaxException {
    stack.clear();

    var sc = new Scanner(tokenize(expression));

    try {
      while (sc.hasNext()) {
        processToken(sc.next());
      }
    } catch (EmptyStackException e) {
      throw new SyntaxException("Premature end-of-stack");
    }

    return stack.peek();
  }
}
