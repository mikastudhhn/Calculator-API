package de.mika.hhn.calculator.ast;

import de.mika.hhn.calculator.spi.Operation;

public class OperationNode implements ExpressionNode {
    private final Operation operation;
    private final ExpressionNode[] operands;

    public OperationNode(Operation operation, ExpressionNode... operands) {
        this.operation = operation;
        this.operands = operands;
    }

    @Override
    public double evaluate() {
        double[] operandValues = new double[operands.length];
        for (int i = 0; i < operands.length; i++) {
            operandValues[i] = operands[i].evaluate();
        }
        return operation.apply(operandValues);
    }
}