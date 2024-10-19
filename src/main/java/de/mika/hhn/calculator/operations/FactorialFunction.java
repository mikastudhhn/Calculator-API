package de.mika.hhn.calculator.operations;

import de.mika.hhn.calculator.spi.Function;
import de.mika.hhn.calculator.spi.Operation;
import de.mika.hhn.calculator.spi.OperationProvider;

public class FactorialFunction implements OperationProvider {

    @Override
    public String getSymbol() {
        return "!";
    }

    @Override
    public Operation getOperation() {
        return new Function() {
            @Override
            public double apply(double[] operands) {
                int n = (int) operands[0];
                if (n < 0) {
                    throw new ArithmeticException("Faculty for negative numbers is not defined.");
                }
                return factorial(n);
            }

            private double factorial(int n) {
                double result = 1;
                for (int i = 2; i <= n; i++) {
                    result *= i;
                }
                return result;
            }

            @Override
            public int getOperandCount() {
                return 1;
            }
        };
    }
}