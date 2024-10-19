package de.mika.hhn.calculator.spi;

import de.mika.hhn.calculator.context.CalculatorContext;

public interface ModeProvider {
    String getSymbol();
    void applyMode(CalculatorContext context);
}