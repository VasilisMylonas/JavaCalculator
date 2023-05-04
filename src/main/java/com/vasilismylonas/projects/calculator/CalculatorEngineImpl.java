package com.vasilismylonas.projects.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

public class CalculatorEngineImpl implements CalculatorEngine {
    private static String[] knownFunctions = {
            "sin", "cos",
            "tan", "cot",
            "sec", "csc",
            "arcsin", "arccos",
            "arctan", "arccot",
            "arcsec", "arccsc",
            "sqrt", "cbrt",
            "ln", "log",
            "abs", "sign",
            "exp",
            // TODO: more functions
    };

    private static String[] knownOperators = {
            "+", "-",
            "*", "/",
    };

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
            case "ln" -> Math::log;
            case "log" -> Math::log10;
            case "abs" -> Math::abs;
            case "sign" -> Math::signum;
            case "exp" -> Math::exp;

            default -> throw new RuntimeException("?"); // TODO
        };
    }

    @Override
    public int getPriority(String operator) {
        return switch (operator) {
            case "*", "/" -> 1;
            case "+", "-" -> 0;
            default ->
                // TODO
                    throw new RuntimeException("?");
        };
    }

    @Override
    public boolean isAssociative(String operator) {
        return operator.equals("*") || operator.equals("-");
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
            return false;
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
