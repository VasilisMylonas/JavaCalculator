package com.vasilismylonas.projects.calculator.engine;

import java.util.Scanner;
import java.util.Stack;

public class PostfixParser implements Parser {
    private final Stack<Double> stack = new Stack<>();
    private final CalculatorEngine engine;

    public PostfixParser(CalculatorEngine engine) {
        this.engine = engine;
    }

    public double parse(String expression) {
        var sc = new Scanner(expression);

        while (sc.hasNext()) {
            var token = sc.next();
            if (engine.isNumber(token)) {
                stack.push(engine.getNumber(token));
            } else {
                if (engine.isFunction(token)) {
                    var arg = stack.pop();
                    var fn = engine.getFunction(token);
                    stack.push(fn.apply(arg));
                } else {
                    // TODO
                    var a = stack.pop();
                    var b = stack.pop();
                    stack.push(
                            switch (token) {
                                case "+" -> b + a;
                                case "-" -> b - a;
                                case "*" -> b * a;
                                case "/" -> b / a;
                                case "^" -> Math.pow(b, a);
                                default -> throw new  RuntimeException("?"); // TODO
                            }
                    );
                }
            }
        }

        var result = stack.peek();
        stack.clear();
        return result;
    }

    public CalculatorEngine getEngine() {
        return engine;
    }
}
