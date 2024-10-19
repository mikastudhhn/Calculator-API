package de.mika.hhn.calculator.formatters;

import de.mika.hhn.calculator.fractions.Fraction;
import de.mika.hhn.calculator.fractions.MixedFraction;

public class FractionFormatter {

    public enum Format {
        PROPER_FRACTION,
        IMPROPER_FRACTION,
        MIXED_FRACTION,
        DECIMAL
    }

    private final Format format;

    public FractionFormatter(Format format) {
        this.format = format;
    }

    public String format(Fraction fraction) {
        return switch (format) {
            case PROPER_FRACTION -> formatProperFraction(fraction);
            case MIXED_FRACTION -> formatMixedFraction(fraction);
            case DECIMAL -> formatDecimal(fraction);
            default -> fraction.toString();
        };
    }

    private String formatProperFraction(Fraction fraction) {
        if (fraction.isImproper()) {
            // Convert to mixed fraction
            MixedFraction mixedFraction = fraction.toMixedFraction();
            return mixedFraction.toString();
        } else {
            return fraction.toString();
        }
    }

    private String formatMixedFraction(Fraction fraction) {
        MixedFraction mixedFraction = fraction.toMixedFraction();
        return mixedFraction.toString();
    }

    private String formatDecimal(Fraction fraction) {
        double decimalValue = (double) fraction.getNumerator() / fraction.getDenominator();
        return String.valueOf(decimalValue);
    }
}