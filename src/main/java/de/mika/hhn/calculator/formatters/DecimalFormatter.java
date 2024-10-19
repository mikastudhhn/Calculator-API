package de.mika.hhn.calculator.formatters;

import java.text.DecimalFormat;

public class DecimalFormatter implements OutputFormatter {
    private int decimalPlaces = 2; // Default to 2 decimal places
    private DecimalFormat decimalFormat;

    public DecimalFormatter() {
        updateDecimalFormat();
    }

    @Override
    public String format(double value) {
        return decimalFormat.format(value);
    }

    @Override
    public void setDecimalPlaces(int decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
        updateDecimalFormat();
    }

    private void updateDecimalFormat() {
        decimalFormat = new DecimalFormat("#." + "#".repeat(Math.max(0, decimalPlaces)));
    }
}