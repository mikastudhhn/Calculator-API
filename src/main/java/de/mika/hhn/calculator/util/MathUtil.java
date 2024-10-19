package de.mika.hhn.calculator.util;

import de.mika.hhn.calculator.context.CalculatorContext;

public class MathUtil {
    public static double adjustAngle(double angle) {
        if (CalculatorContext.getInstance().getAngleMode() == CalculatorContext.AngleMode.DEGREES) {
            return Math.toRadians(angle);
        } else {
            return angle;
        }
    }
}