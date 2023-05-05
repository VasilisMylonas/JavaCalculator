package com.vasilismylonas.projects.calculator.engine;

import java.util.Scanner;
import java.util.Stack;

public class PostfixParser implements Parser {
    private final Stack<String> stack = new Stack<>();
    private final CalculatorEngine engine;

    public PostfixParser(CalculatorEngine engine) {
        this.engine = engine;
    }

    public double parse(String expression) {
        var sc = new Scanner(expression);

        while (sc.hasNext()) {
            var token = sc.next();
            if (engine.isNumber(token)) {
                stack.push(token);
            } else {
                if (engine.isFunction(token)) {
                    var arg = Double.parseDouble(stack.pop());
                    var fn = engine.getFunction(token);
                    stack.push(String.valueOf(fn.apply(arg)));
                } else {
                    // TODO
                    var a = Double.parseDouble(stack.pop());
                    var b = Double.parseDouble(stack.pop());
                    stack.push(String.valueOf(
                            switch (token) {
                                case "+" -> b + a;
                                case "-" -> b - a;
                                case "*" -> b * a;
                                case "/" -> b / a;
                                default -> throw new RuntimeException("?"); // TODO
                            }
                    ));
                }
            }
        }

        var result = Double.parseDouble(stack.peek());
        stack.clear();
        return result;
    }

    public CalculatorEngine getEngine() {
        return engine;
    }
}
