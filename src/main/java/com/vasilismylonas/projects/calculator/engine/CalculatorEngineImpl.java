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
            // TODO: more functions
    };

    private static final String[] specialNumbers = {
            "π", "e"
    };

    private static final String[] knownOperators = {
            "+", "-",
            "*", "/",
            "^"
    };

    private int factorial(int n) {
        if (n == 0) {
            return 1;
        }

        return n * factorial(n - 1);
    }

    @Override
    public Function<Double, Double> getFunction(String name) {
        return switch (name) {
            // Trig functions
            case "sin" -> Math::sin;
            case "cos" -> Math::cos;
            case "tan" -> Math::tan;
            case "cot" -> (Double arg) -> 1 / Math.tan(arg);
            case "sec" -> (Double arg) -> 1 / Math.cos(arg);
            case "csc" -> (Double arg) -> 1 / Math.sin(arg);
            case "arcsin" -> Math::asin;
            case "arccos" -> Math::acos;
            case "arctan" -> Math::atan;
            case "arccot" -> (Double arg) -> 1 / Math.atan(arg);
            case "arcsec" -> (Double arg) -> 1 / Math.acos(arg);
            case "arccsc" -> (Double arg) -> 1 / Math.asin(arg);

            // Common
            case "sqrt" -> Math::sqrt;
            case "cbrt" -> Math::cbrt;
            case "log" -> Math::log;
            case "abs" -> Math::abs;
            case "sign" -> Math::signum;
            case "exp" -> Math::exp;
            case "deg" -> Math::toRadians;
            case "floor" -> Math::floor;
            case "ceil" -> Math::ceil;
            case "fact" -> (Double arg) -> {
                if (Math.floor(arg) == arg) {
                    return Double.valueOf(factorial(arg.intValue()));
                }

                throw new ArithmeticException("Non-integer factorial cannot be computed.");
            };

            default -> throw new RuntimeException("?"); // TODO
        };
    }

    @Override
    public int getPriority(String operator) {
        return switch (operator) {
            case "^" -> 2;
            case "*", "/" -> 1;
            case "+", "-" -> 0;
            default ->
                // TODO
                    throw new RuntimeException("?");
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
