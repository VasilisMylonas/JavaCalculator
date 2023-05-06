package com.vasilismylonas.projects.calculator.engine;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

public class CalculatorEngineImpl implements CalculatorEngine {
    private static final String[] knownFunctions = {
            "sin", "cos",
            "tan", "cot",
            "sec", "csc",
            "arcsin", "arccos",
            "arctan", "arccot",
            "arcsec", "arccsc",
            "sqrt", "cbrt",
            "log", "exp",
            "abs", "sign",
            "deg", "fact",
            "floor", "ceil"
    };

    private static final String[] specialNumbers = {
            "π", "e"
    };

    private static final String[] knownOperators = {
            "+", "-",
            "*", "/",
            "^"
    };

    private double factorial(double arg) {
        if (Math.floor(arg) != arg || arg < 0) {
            throw new ArithmeticException("Undefined factorial");
        }

        if (arg == 0) {
            return 1;
        }

        return arg * factorial(arg - 1);
    }

    @Override
    public double evalFunction(String function, double arg) {
        return switch (function) {
            // Trig functions
            case "sin" -> Math.sin(arg);
            case "cos" -> Math.cos(arg);
            case "tan" -> Math.tan(arg);
            case "cot" -> 1 / Math.tan(arg);
            case "sec" -> 1 / Math.cos(arg);
            case "csc" -> 1 / Math.sin(arg);
            case "arcsin" -> Math.asin(arg);
            case "arccos" -> Math.acos(arg);
            case "arctan" -> Math.atan(arg);
            case "arccot" -> 1 / Math.atan(arg);
            case "arcsec" -> 1 / Math.acos(arg);
            case "arccsc" -> 1 / Math.asin(arg);

            // Common
            case "sqrt" -> Math.sqrt(arg);
            case "cbrt" -> Math.cbrt(arg);
            case "log" -> Math.log(arg);
            case "abs" -> Math.abs(arg);
            case "sign" -> Math.signum(arg);
            case "exp" -> Math.exp(arg);
            case "deg" -> Math.toRadians(arg);
            case "floor" -> Math.floor(arg);
            case "ceil" -> Math.ceil(arg);
            case "fact" -> factorial(arg);
            default -> throw new IllegalArgumentException(function);
        };
    }

    @Override
    public int getPriority(String operator) {
        return switch (operator) {
            case "^" -> 2;
            case "*", "/" -> 1;
            case "+", "-" -> 0;
            default -> throw new IllegalArgumentException(operator);
        };
    }

    @Override
    public double getNumber(String name) {
        if (name.equals("π")) {
            return Math.PI;
        }

        if (name.equals("e")) {
            return Math.E;
        }

        return Double.parseDouble(name);
    }

    @Override
    public double evalOperator(String operator, double a, double b) {
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            case "^" -> Math.pow(a, b);
            default -> throw new IllegalArgumentException(operator);
        };
    }

    @Override
    public boolean isAssociative(String operator) {
        return operator.equals("*") || operator.equals("+");
    }

    @Override
    public boolean isFunction(String token) {
        return Arrays.asList(knownFunctions).contains(token);
    }

    @Override
    public boolean isOperator(String token) {
        return Arrays.asList(knownOperators).contains(token);
    }

    @Override
    public boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException ex) {
            return Arrays.asList(specialNumbers).contains(token);
        }
    }

    @Override
    public Collection<String> getFunctions() {
        return Arrays.asList(knownFunctions);
    }

    @Override
    public Collection<String> getOperators() {
        return Arrays.asList(knownOperators);
    }
}
