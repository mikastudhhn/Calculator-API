package de.mika.hhn.calculator.formatters;

public class ScientificFormatter implements OutputFormatter {

    @Override
    public String format(double result) {
        return String.format("%e", result);
    }

    @Override
    public void setDecimalPlaces(int decimalPlaces) {
        //TODO: ADD CONTENT
    }
}