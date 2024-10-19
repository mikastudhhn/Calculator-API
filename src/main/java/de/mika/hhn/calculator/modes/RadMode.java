package de.mika.hhn.calculator.modes;

import de.mika.hhn.calculator.context.CalculatorContext;
import de.mika.hhn.calculator.spi.ModeProvider;

public class RadMode implements ModeProvider {

    @Override
    public String getSymbol() {
        return "rad";
    }

    @Override
    public void applyMode(CalculatorContext context) {
        context.setAngleMode(CalculatorContext.AngleMode.RADIANS);
    }
}