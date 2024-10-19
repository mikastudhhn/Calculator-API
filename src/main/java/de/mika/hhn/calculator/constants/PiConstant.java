package de.mika.hhn.calculator.constants;

import de.mika.hhn.calculator.spi.ConstantProvider;

public class PiConstant implements ConstantProvider {
    @Override
    public String getSymbol() {
        return "pi";
    }

    @Override
    public double getValue() {
        return Math.PI;
    }
}