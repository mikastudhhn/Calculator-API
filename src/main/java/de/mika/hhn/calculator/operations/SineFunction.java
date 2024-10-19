package de.mika.hhn.calculator.operations;

import de.mika.hhn.calculator.spi.Function;
import de.mika.hhn.calculator.spi.Operation;
import de.mika.hhn.calculator.spi.OperationProvider;
import de.mika.hhn.calculator.util.MathUtil;

public class SineFunction implements OperationProvider {

    @Override
    public String getSymbol() {
        return "sin";
    }

    @Override
    public Operation getOperation() {
        return new Function() {
            @Override
            public double apply(double[] operands) {
                return Math.sin(MathUtil.adjustAngle(operands[0]));
            }

            @Override
            public int getOperandCount() {
                return 1;
            }
        };
    }
}