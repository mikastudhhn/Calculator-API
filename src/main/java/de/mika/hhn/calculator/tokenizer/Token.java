package de.mika.hhn.calculator.tokenizer;

public class Token {
    public enum Type {
        NUMBER,
        VARIABLE,
        OPERATOR,
        FUNCTION,
        CONSTANT,
        LEFT_PAREN,
        RIGHT_PAREN,
        MODE
    }

    private final Type type;
    private final String value;

    public Token(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}