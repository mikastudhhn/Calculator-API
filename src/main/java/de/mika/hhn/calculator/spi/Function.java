package de.mika.hhn.calculator.spi;

public interface Function extends Operation {
    @Override
    default int getPrecedence() {
        return 4;
    }

    @Override
    default Associativity getAssociativity() {
        return Associativity.LEFT;
    }
}