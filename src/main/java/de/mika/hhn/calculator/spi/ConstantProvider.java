package de.mika.hhn.calculator.spi;

public interface ConstantProvider {
    String getSymbol();
    double getValue();
}