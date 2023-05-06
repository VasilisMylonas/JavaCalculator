package com.vasilismylonas.projects.calculator.engine;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class InfixParser extends PostfixParser {
    private final Queue<String> output = new ArrayDeque<>();
    private final ArrayDeque<String> operators = new ArrayDeque<>();

    private boolean isMoreImportant(String o1, String o2) {
        return getEngine().getPriority(o2) > getEngine().getPriority(o1) ||
                (getEngine().getPriority(o2) == getEngine().getPriority(o1) && getEngine().isAssociative(o1));
    }

    private void processToken(String token) throws SyntaxException {
        if (getEngine().isNumber(token)) {
            output.add(token);
        } else if (getEngine().isFunction(token)) {
            operators.push(token);
        } else if (getEngine().isOperator(token)) {
            while (!operators.isEmpty() && !operators.peek().equals("(") && isMoreImportant(token, operators.peek())) {
                output.add(operators.pop());
            }
            operators.push(token);
        } else if (token.equals("(")) {
            operators.push(token);
        } else if (token.equals(")")) {
            while (!operators.isEmpty() && !operators.peek().equals("(")) {
                output.add(operators.pop());
            }

            if (operators.isEmpty() || !operators.peek().equals("(")) {
                throw new SyntaxException("Expected (");
            }

            operators.pop(); // Discard matching (

            if (getEngine().isFunction(operators.peek())) {
                output.add(operators.pop());
            }
        } else if (!token.equals(",")) {
            throw new SyntaxException("Unexpected: " + token);
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

    private String toPostfix(String expression) throws SyntaxException {
        output.clear();
        operators.clear();

        var sc = new Scanner(expression);

        while (sc.hasNext()) {
            processToken(sc.next());
        }

        while (!operators.isEmpty()) {
            if (operators.peek().equals(")")) {
                throw new SyntaxException("Unexpected )");
            }
            output.add(operators.pop());
        }

        return queueToString(output);
    }

    public double parse(String expression) throws SyntaxException {
        return super.parse(toPostfix(tokenize(expression)));
    }

    public InfixParser(CalculatorEngine engine) {
        super(engine);
    }
}
