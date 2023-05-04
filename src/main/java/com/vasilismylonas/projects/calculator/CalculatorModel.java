package com.vasilismylonas.projects.calculator;

public class CalculatorModel {
    private Parser infixParser;
    private Parser postfixParser;
    private ParserMode parserMode = ParserMode.INFIX;

    public CalculatorModel(CalculatorEngine engine) {
        infixParser = new InfixParser(engine);
        postfixParser = new PostfixParser(engine);
    }

    private String tokenize(String expression) {
        return expression;
        // TODO
//        var builder = new StringBuilder(expression.length());
//
//        builder.append(" ");
//
//        for (int i = 0; i < expression.length(); i++) {
//            var c = expression.charAt(i);
//            if (Character.isDigit(c) || c == '.') {
//                builder.append(c);
//            } else {
//                builder.append(" ");
//                builder.append(c);
//                builder.append(" ");
//            }
//        }
//
//        builder.append(" ");
//
//        return builder.toString();
    }

    public double eval(String expression) {
        return getParser().parse(tokenize(expression));
    }

    public Parser getParser() {
        return switch (parserMode) {
            case INFIX -> infixParser;
            case POSTFIX -> postfixParser;
        };
    }

    public ParserMode getParserMode() {
        return parserMode;
    }

    public void setParserMode(ParserMode parserMode) {
        this.parserMode = parserMode;
    }
}
