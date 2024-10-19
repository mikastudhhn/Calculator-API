package de.mika.hhn.calculator.util;

import de.mika.hhn.calculator.fractions.Fraction;

public class FractionUtils {

    public static Fraction toFraction(double decimal) {
        final double EPSILON = 1e-10; // Tolerance for floating point comparisons
        long maxDenominator = 1000000L; // Limit denominator size to prevent overflow

        if (Double.isNaN(decimal) || Double.isInfinite(decimal)) {
            throw new ArithmeticException("Cannot convert NaN or Infinite values to fractions.");
        }

        boolean isNegative = decimal < 0;
        decimal = Math.abs(decimal);

        long wholePart = (long) decimal;
        double fractionalPart = decimal - wholePart;

        if (fractionalPart < EPSILON) {
            long numerator = wholePart;
            if (isNegative) {
                numerator = -numerator;
            }
            return new Fraction(numerator, 1);
        }


        // Continued fraction algorithm
        long numerator = 1;
        long denominator = 0;
        long prevNumerator = 0;
        long prevDenominator = 1;
        double fraction = fractionalPart;

        while (true) {
            long a = (long) (fraction);
            long tempNumerator = numerator;
            long tempDenominator = denominator;

            numerator = a * numerator + prevNumerator;
            denominator = a * denominator + prevDenominator;

            prevNumerator = tempNumerator;
            prevDenominator = tempDenominator;

            if (denominator > maxDenominator) {
                break;
            }

            double approximatedFraction = (double) numerator / denominator;
            if (Math.abs(approximatedFraction - fractionalPart) < EPSILON) {
                break;
            }

            fraction = 1.0 / (fraction - a);
        }

        numerator += wholePart * denominator;
        if (isNegative) {
            numerator = -numerator;
        }

        return new Fraction(numerator, denominator);
    }
}