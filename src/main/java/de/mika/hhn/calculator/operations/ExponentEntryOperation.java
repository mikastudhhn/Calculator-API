package de.mika.hhn.calculator.operations;

import de.mika.hhn.calculator.spi.Operation;
import de.mika.hhn.calculator.spi.OperationProvider;

public class ExponentEntryOperation implements OperationProvider {

    @Override
    public String getSymbol() {
        return "ee";
    }

    @Override
    public Operation getOperation() {
        return new Operation() {
            @Override
            public double apply(double[] operands) {
                return operands[0] * Math.pow(10, operands[1]);
            }

            @Override
            public int getOperandCount() {
                return 2;
            }

            @Override
            public int getPrecedence() {
                return 3;
            }

            @Override
            public Associativity getAssociativity() {
                return Associativity.LEFT;
            }
        };
    }
}