package de.mika.hhn.calculator.operations;

import de.mika.hhn.calculator.spi.Operation;
import de.mika.hhn.calculator.spi.OperationProvider;

import java.util.Arrays;

public class MultiplicationOperation implements OperationProvider {

    @Override
    public String getSymbol() {
        return "*";
    }

    @Override
    public Operation getOperation() {
        return new Operation() {
            @Override
            public double apply(double... operands) {
                return Arrays.stream(operands).reduce((a, b) -> a * b).orElse(1);
            }

            @Override
            public int getPrecedence() {
                return 2;
            }

            @Override
            public int getOperandCount() {
                return 2;
            }

            @Override
            public Associativity getAssociativity() {
                return Associativity.LEFT;
            }
        };
    }
}