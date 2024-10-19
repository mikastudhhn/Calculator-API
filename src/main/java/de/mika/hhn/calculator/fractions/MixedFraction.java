package de.mika.hhn.calculator.fractions;

public class MixedFraction {
    private long wholePart;
    private long numerator;
    private long denominator;

    public MixedFraction(long wholePart, long numerator, long denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Denominator cannot be zero.");
        }
        this.wholePart = wholePart;
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public long getWholePart() {
        return wholePart;
    }

    public long getNumerator() {
        return numerator;
    }

    public long getDenominator() {
        return denominator;
    }

    // Override toString for mixed fraction
    @Override
    public String toString() {
        if (numerator == 0) {
            return String.valueOf(wholePart);
        } else {
            return wholePart + " " + numerator + "/" + denominator;
        }
    }
}