package de.mika.hhn.calculator.formatters;

public interface OutputFormatter {
    String format(double value);
    void setDecimalPlaces(int decimalPlaces);
}