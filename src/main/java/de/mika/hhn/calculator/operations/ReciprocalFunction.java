package de.mika.hhn.calculator.operations;

import de.mika.hhn.calculator.spi.Function;
import de.mika.hhn.calculator.spi.Operation;
import de.mika.hhn.calculator.spi.OperationProvider;

public class ReciprocalFunction implements OperationProvider {

    @Override
    public String getSymbol() {
        return "recip";
    }

    @Override
    public Operation getOperation() {
        return new Function() {
            @Override
            public double apply(double[] operands) {
                return Math.pow(10, operands[0]);
            }

            @Override
            public int getOperandCount() {
                return 1;
            }

        };
    }
}