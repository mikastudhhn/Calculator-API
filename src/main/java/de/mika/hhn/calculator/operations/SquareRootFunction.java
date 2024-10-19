package de.mika.hhn.calculator.operations;

import de.mika.hhn.calculator.spi.Function;
import de.mika.hhn.calculator.spi.Operation;
import de.mika.hhn.calculator.spi.OperationProvider;

public class SquareRootFunction implements OperationProvider {

    @Override
    public String getSymbol() {
        return "sqrt";
    }

    @Override
    public Operation getOperation() {
        return new Function() {
            @Override
            public double apply(double[] operands) {
                if (operands[0] < 0) {
                    throw new ArithmeticException("Root of negative number is not defined.");
                }
                return Math.sqrt(operands[0]);
            }

            @Override
            public int getOperandCount() {
                return 1;
            }
        };
    }
}