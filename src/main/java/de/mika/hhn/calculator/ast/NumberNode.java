package de.mika.hhn.calculator.ast;

public class NumberNode implements ExpressionNode {
    private final double value;

    public NumberNode(double value) {
        this.value = value;
    }

    @Override
    public double evaluate() {
        return value;
    }
}