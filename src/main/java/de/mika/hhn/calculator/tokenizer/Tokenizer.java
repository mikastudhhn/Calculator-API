package de.mika.hhn.calculator.tokenizer;

import de.mika.hhn.calculator.exceptions.TokenizerException;
import de.mika.hhn.calculator.factories.OperationFactory;
import de.mika.hhn.calculator.factories.ConstantFactory;
import de.mika.hhn.calculator.factories.ModeFactory;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    public List<Token> tokenize(String expression) throws TokenizerException {
        List<Token> tokens = new ArrayList<>();
        int index = 0;
        int length = expression.length();

        while (index < length) {
            char currentChar = expression.charAt(index);

            // Skip whitespace
            if (Character.isWhitespace(currentChar)) {
                index++;
                continue;
            }

            // Recognize numbers
            if (Character.isDigit(currentChar) || currentChar == '.') {
                StringBuilder number = new StringBuilder();
                boolean hasDecimalPoint = false;

                while (index < length && (Character.isDigit(expression.charAt(index)) || expression.charAt(index) == '.')) {
                    if (expression.charAt(index) == '.') {
                        if (hasDecimalPoint) {
                            throw new TokenizerException("Invalid number format: multiple decimal points");
                        }
                        hasDecimalPoint = true;
                    }
                    number.append(expression.charAt(index));
                    index++;
                }

                tokens.add(new Token(Token.Type.NUMBER, number.toString()));
                continue;
            }

            // Recognize functions, constants, and modes
            if (Character.isLetter(currentChar)) {
                StringBuilder name = new StringBuilder();
                while (index < length && Character.isLetter(expression.charAt(index))) {
                    name.append(expression.charAt(index));
                    index++;
                }
                String nameStr = name.toString().toLowerCase();

                if (ModeFactory.isMode(nameStr)) {
                    tokens.add(new Token(Token.Type.MODE, nameStr));
                } else if (ConstantFactory.isConstant(nameStr)) {
                    tokens.add(new Token(Token.Type.CONSTANT, nameStr));
                } else if (OperationFactory.isOperation(nameStr)) {
                    tokens.add(new Token(Token.Type.FUNCTION, nameStr));
                } else {
                    throw new TokenizerException("Unknown token: " + nameStr);
                }
                continue;
            }

            // Recognize operators
            String opChar = String.valueOf(currentChar);
            if (OperationFactory.isOperation(opChar)) {
                tokens.add(new Token(Token.Type.OPERATOR, opChar));
                index++;
                continue;
            }

            // Recognize parentheses
            if (currentChar == '(') {
                tokens.add(new Token(Token.Type.LEFT_PAREN, "("));
                index++;
                continue;
            }

            if (currentChar == ')') {
                tokens.add(new Token(Token.Type.RIGHT_PAREN, ")"));
                index++;
                continue;
            }

            // Unrecognized character
            throw new TokenizerException("Unknown character: " + currentChar);
        }

        return tokens;
    }
}