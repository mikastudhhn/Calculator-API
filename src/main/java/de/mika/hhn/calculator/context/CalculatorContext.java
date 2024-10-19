package de.mika.hhn.calculator.context;

public class CalculatorContext {
    private static CalculatorContext instance;

    public enum AngleMode {
        DEGREES,
        RADIANS,
        GRADIANS
    }

    private AngleMode angleMode = AngleMode.RADIANS; // Default is radians

    private CalculatorContext() {}

    public static CalculatorContext getInstance() {
        if (instance == null)
        {
            instance = new CalculatorContext();
        }
        return instance;
    }

    public AngleMode getAngleMode() {
        return angleMode;
    }

    public void setAngleMode(AngleMode angleMode) {
        this.angleMode = angleMode;
    }
}