package de.mika.hhn.calculator.constants;

import de.mika.hhn.calculator.spi.ConstantProvider;

public class EConstant implements ConstantProvider {

    @Override
    public String getSymbol() {
        return "e";
    }

    @Override
    public double getValue() {
        return Math.E;
    }
}