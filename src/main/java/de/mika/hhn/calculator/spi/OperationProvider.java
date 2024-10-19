package de.mika.hhn.calculator.spi;

public interface OperationProvider {
    String getSymbol();
    Operation getOperation();
}