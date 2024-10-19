package de.mika.hhn.calculator.modes;

import de.mika.hhn.calculator.context.CalculatorContext;
import de.mika.hhn.calculator.spi.ModeProvider;

public class DegMode implements ModeProvider {

    @Override
    public String getSymbol() {
        return "deg";
    }

    @Override
    public void applyMode(CalculatorContext context) {
        context.setAngleMode(CalculatorContext.AngleMode.DEGREES);
    }
}