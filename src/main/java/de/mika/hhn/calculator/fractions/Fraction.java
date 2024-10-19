package de.mika.hhn.calculator.fractions;

public class Fraction {
    private long numerator;
    private long denominator;

    public Fraction(long numerator, long denominator) {
        // Ensure denominator is positive
        if (denominator == 0) {
            throw new ArithmeticException("Denominator cannot be zero.");
        } else if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        // Simplify the fraction
        long gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    // Getter methods
    public long getNumerator() {
        return numerator;
    }

    public long getDenominator() {
        return denominator;
    }

    // Check if the fraction is improper
    public boolean isImproper() {
        return Math.abs(numerator) >= denominator;
    }

    // Convert to mixed fraction
    public MixedFraction toMixedFraction() {
        long wholePart = numerator / denominator;
        long fractionalNumerator = Math.abs(numerator % denominator);
        return new MixedFraction(wholePart, fractionalNumerator, denominator);
    }

    // Override toString for improper fraction
    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    // Helper method to compute GCD
    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}