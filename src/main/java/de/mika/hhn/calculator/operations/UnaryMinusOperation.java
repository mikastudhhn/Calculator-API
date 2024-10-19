package de.mika.hhn.calculator.operations;

import de.mika.hhn.calculator.spi.Operation;
import de.mika.hhn.calculator.spi.OperationProvider;

public class UnaryMinusOperation implements OperationProvider {

    @Override
    public String getSymbol() {
        return "unary_-";
    }

    @Override
    public Operation getOperation() {
        return new Operation() {
            @Override
            public double apply(double[] operands) {
                return -operands[0];
            }

            @Override
            public int getOperandCount() {
                return 1;
            }

            @Override
            public int getPrecedence() {
                return 5; // Higher precedence than binary minus
            }

            @Override
            public Associativity getAssociativity() {
                return Associativity.RIGHT;
            }
        };
    }
}