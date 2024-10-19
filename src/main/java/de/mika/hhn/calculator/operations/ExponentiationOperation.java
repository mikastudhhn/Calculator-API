package de.mika.hhn.calculator.operations;

import de.mika.hhn.calculator.spi.Operation;
import de.mika.hhn.calculator.spi.OperationProvider;

public class ExponentiationOperation implements OperationProvider {

    @Override
    public String getSymbol() {
        return "^";
    }

    @Override
    public Operation getOperation() {
        return new Operation() {
            @Override
            public double apply(double[] operands) {
                return Math.pow(operands[0], operands[1]);
            }

            @Override
            public int getPrecedence() {
                return 3;
            }

            @Override
            public int getOperandCount() {
                return 2;
            }

            @Override
            public Operation.Associativity getAssociativity() {
                return Associativity.RIGHT; // Exponentiation ist rechtsassoziativ
            }
        };
    }
}