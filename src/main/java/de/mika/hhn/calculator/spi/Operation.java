package de.mika.hhn.calculator.spi;

public interface Operation {
    double apply(double[] operands);
    int getOperandCount();
    int getPrecedence();
    Associativity getAssociativity();

    enum Associativity {
        LEFT,
        RIGHT,
        NONE
    }
}
