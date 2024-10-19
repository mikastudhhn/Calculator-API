package de.mika.hhn.calculator.api;

import de.mika.hhn.calculator.ast.ExpressionNode;
import de.mika.hhn.calculator.exceptions.ParsingException;
import de.mika.hhn.calculator.exceptions.TokenizerException;
import de.mika.hhn.calculator.formatters.DecimalFormatter;
import de.mika.hhn.calculator.formatters.FractionFormatter;
import de.mika.hhn.calculator.formatters.OutputFormatter;
import de.mika.hhn.calculator.fractions.Fraction;
import de.mika.hhn.calculator.parser.ExpressionParser;
import de.mika.hhn.calculator.util.FractionUtils;

public class CalculatorAPI {

    private OutputFormatter formatter;
    private boolean alwaysShowDenominator = true;
    private FractionFormatter.Format fractionFormat = FractionFormatter.Format.MIXED_FRACTION;


    public CalculatorAPI() {
        formatter = new DecimalFormatter();
    }

    public CalculatorAPI(OutputFormatter outputFormatter) {
        formatter = outputFormatter;
    }

    public void setDecimalPlaces(int decimalPlaces) {
        formatter.setDecimalPlaces(decimalPlaces);
    }

    public String evaluateString(String expression) throws ParsingException, TokenizerException {
        return formatter.format(evaluateDouble(expression));
    }

    public double evaluateDouble(String expression) throws ParsingException, TokenizerException {
        ExpressionParser parser = new ExpressionParser();
        ExpressionNode ast = parser.parse(expression);
        return ast.evaluate();
    }

    public String computeFraction(String expression) throws ParsingException, TokenizerException {
        ExpressionParser parser = new ExpressionParser();
        ExpressionNode ast = parser.parse(expression);
        double result = evaluateDouble(expression);
        Fraction fraction = FractionUtils.toFraction(result);

        FractionFormatter formatter = new FractionFormatter(fractionFormat);
        return formatter.format(fraction);
    }

    public void setAlwaysShowDenominator(boolean alwaysShowDenominator) {
        this.alwaysShowDenominator = alwaysShowDenominator;
    }

    public boolean isAlwaysShowDenominator() {
        return alwaysShowDenominator;
    }

    public void setFormatter(OutputFormatter formatter) {
        this.formatter = formatter;
    }

    public OutputFormatter getFormatter() {
        return formatter;
    }

    public void setFractionFormat(FractionFormatter.Format format) {
        this.fractionFormat = format;
    }
}