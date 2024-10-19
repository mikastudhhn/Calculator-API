package de.mika.hhn.calculator.operations;

import de.mika.hhn.calculator.spi.Function;
import de.mika.hhn.calculator.spi.Operation;
import de.mika.hhn.calculator.spi.OperationProvider;

public class RootFunction implements OperationProvider {

    @Override
    public String getSymbol() {
        return "root";
    }

    @Override
    public Operation getOperation() {
        return new Function() {
            @Override
            public double apply(double[] operands) {
                if (operands[1] == 0) {
                    throw new ArithmeticException("Root exponent must not be zero.");
                }
                return Math.pow(operands[0], 1 / operands[1]);
            }

            @Override
            public int getOperandCount() {
                return 2;
            }
        };
    }
}