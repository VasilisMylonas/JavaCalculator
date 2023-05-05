package com.vasilismylonas.projects.calculator.engine;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class InfixParser implements Parser {
    private final Queue<String> output = new ArrayDeque<>();
    private final ArrayDeque<String> operators = new ArrayDeque<>();
    private final CalculatorEngine engine;
    private final PostfixParser parser;

    private boolean isMoreImportant(String o1, String o2) {
        return engine.getPriority(o2) > engine.getPriority(o1) ||
                (engine.getPriority(o2) == engine.getPriority(o1) && !engine.isAssociative(o1));
    }

    private void processToken(String token) {
        if (engine.isNumber(token)) {
            output.add(token);
        } else if (engine.isFunction(token)) {
            operators.push(token);
        } else if (engine.isOperator(token)) {
            // TODO: This part is a bit weird
            var o2 = operators.peek();
            while (o2 != null && !o2.equals("(") && isMoreImportant(token, o2)) {
                output.add(operators.pop());
                o2 = operators.peek();
            }
            operators.push(token);
            //} else if (token == ",") { TODO?
        } else if (token.equals("(")) {
            operators.push(token);
        } else if (token.equals(")")) {
            try {
                while (!operators.peek().equals("(")) {
                    output.add(operators.pop());
                }
            } catch (/*EmptyStackException ex*/ NullPointerException ex2) {
                throw new RuntimeException("MISMATCHED PARENTHESIS!");
            }
//            if (!operators.peek().equals("(")) {
//                throw new RuntimeException("NO (");
//            } TODO

            operators.pop(); // Discard matching (

            if (engine.isFunction(operators.peek())) {
                output.add(operators.pop());
            }
        } else {
            throw new RuntimeException("Unknown token: " + token);
        }
    }

    private String queueToString(Queue<String> queue) {
        StringBuilder builder = new StringBuilder();

        while (!queue.isEmpty()) {
            builder.append(queue.remove());
            builder.append(" ");
        }

        return builder.toString();
    }

    private String toPostfix(String expression) {
        var sc = new Scanner(expression);

        while (sc.hasNext()) {
            processToken(sc.next());
        }

        while (!operators.isEmpty()) {
            if (operators.peek().equals("(")) {
                throw new RuntimeException("IS )");
            }
            output.add(operators.pop());
        }

        var s = queueToString(output);
        output.clear();
        operators.clear();
        return s;
    }

    public double parse(String expression) {
        return parser.parse(toPostfix(expression));
    }

    public CalculatorEngine getEngine() {
        return engine;
    }

    public InfixParser(CalculatorEngine engine) {
        this.engine = engine;
        this.parser = new PostfixParser(engine);
    }
}
