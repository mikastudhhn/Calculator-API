package de.mika.hhn.calculator.operations;

import de.mika.hhn.calculator.spi.Function;
import de.mika.hhn.calculator.spi.Operation;
import de.mika.hhn.calculator.spi.OperationProvider;
import de.mika.hhn.calculator.util.MathUtil;

public class CosineFunction implements OperationProvider {

    @Override
    public String getSymbol() {
        return "cos";
    }

    @Override
    public Operation getOperation() {
        return new Function() {
            @Override
            public double apply(double[] operands) {
                return Math.cos(MathUtil.adjustAngle(operands[0]));
            }

            @Override
            public int getOperandCount() {
                return 1;
            }
        };
    }
}