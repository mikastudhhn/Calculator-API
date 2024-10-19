package de.mika.hhn.calculator.operations;

import de.mika.hhn.calculator.spi.Function;
import de.mika.hhn.calculator.spi.Operation;
import de.mika.hhn.calculator.spi.OperationProvider;

public class NaturalLogFunction implements OperationProvider {

    @Override
    public String getSymbol() {
        return "ln";
    }

    @Override
    public Operation getOperation() {
        return new Function() {
            @Override
            public double apply(double[] operands) {
                if (operands[0] <= 0) {
                    throw new ArithmeticException("Logarithm of non-positive numbers is undefined.");
                }
                return Math.log(operands[0]);
            }

            @Override
            public int getOperandCount() {
                return 1;
            }
        };
    }
}