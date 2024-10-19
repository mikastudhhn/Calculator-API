package de.mika.hhn.calculator.parser;

import de.mika.hhn.calculator.ast.ExpressionNode;
import de.mika.hhn.calculator.ast.NumberNode;
import de.mika.hhn.calculator.ast.OperationNode;
import de.mika.hhn.calculator.context.CalculatorContext;
import de.mika.hhn.calculator.exceptions.ParsingException;
import de.mika.hhn.calculator.exceptions.TokenizerException;
import de.mika.hhn.calculator.factories.ConstantFactory;
import de.mika.hhn.calculator.factories.ModeFactory;
import de.mika.hhn.calculator.factories.OperationFactory;
import de.mika.hhn.calculator.spi.Operation;
import de.mika.hhn.calculator.tokenizer.Token;
import de.mika.hhn.calculator.tokenizer.Tokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ExpressionParser {

    private final Tokenizer tokenizer = new Tokenizer();

    public ExpressionNode parse(String expression) throws ParsingException, TokenizerException {
        List<Token> tokens = tokenizer.tokenize(expression);
        return parseExpression(tokens);
    }

    private ExpressionNode parseExpression(List<Token> tokens) throws ParsingException {
        List<Token> rpnTokens = convertToRPN(tokens);
        return buildAST(rpnTokens);
    }

    private List<Token> convertToRPN(List<Token> tokens) throws ParsingException {
        List<Token> outputQueue = new ArrayList<>();
        Stack<Token> operatorStack = new Stack<>();

        Token previousToken = null;

        for (Token token : tokens) {
            switch (token.getType()) {
                case NUMBER:
                case CONSTANT:
                    outputQueue.add(token);
                    break;

                case FUNCTION:
                    operatorStack.push(token);
                    break;

                case OPERATOR:
                    String symbol = token.getValue();
                    Operation o1 = OperationFactory.getOperation(symbol);

                    if (o1 == null) {
                        throw new ParsingException("Unknown operator: " + symbol);
                    }

                    // Check if the operator is unary
                    if (isUnaryOperator(token, previousToken)) {
                        // Replace the token's value with 'unary_' prefix to differentiate
                        token = new Token(Token.Type.OPERATOR, "unary_" + symbol);
                        o1 = OperationFactory.getOperation(token.getValue());
                        if (o1 == null) {
                            throw new ParsingException("Unknown operator: " + token.getValue());
                        }
                    }

                    while (!operatorStack.isEmpty() && (operatorStack.peek().getType() == Token.Type.OPERATOR ||
                            operatorStack.peek().getType() == Token.Type.FUNCTION)) {
                        Token topToken = operatorStack.peek();
                        Operation o2 = OperationFactory.getOperation(topToken.getValue());
                        if (o2 != null && ((o1.getAssociativity() == Operation.Associativity.LEFT
                                && o1.getPrecedence() <= o2.getPrecedence())
                                || (o1.getAssociativity() == Operation.Associativity.RIGHT
                                && o1.getPrecedence() < o2.getPrecedence()))) {
                            outputQueue.add(operatorStack.pop());
                        } else {
                            break;
                        }
                    }

                    operatorStack.push(token);
                    break;

                case LEFT_PAREN:
                    operatorStack.push(token);
                    break;

                case RIGHT_PAREN:
                    while (!operatorStack.isEmpty() && operatorStack.peek().getType() != Token.Type.LEFT_PAREN) {
                        outputQueue.add(operatorStack.pop());
                    }
                    if (operatorStack.isEmpty() || operatorStack.peek().getType() != Token.Type.LEFT_PAREN) {
                        throw new ParsingException("Mismatched parentheses");
                    }
                    operatorStack.pop();
                    if (!operatorStack.isEmpty() && operatorStack.peek().getType() == Token.Type.FUNCTION) {
                        outputQueue.add(operatorStack.pop());
                    }
                    break;

                case MODE:
                    outputQueue.add(token);
                    break;

                default:
                    throw new ParsingException("Unknown token: " + token.getValue());
            }

            previousToken = token;
        }

        while (!operatorStack.isEmpty()) {
            Token topToken = operatorStack.pop();
            if (topToken.getType() == Token.Type.LEFT_PAREN || topToken.getType() == Token.Type.RIGHT_PAREN) {
                throw new ParsingException("Mismatched parentheses");
            }
            outputQueue.add(topToken);
        }

        return outputQueue;
    }

    private ExpressionNode buildAST(List<Token> rpnTokens) throws ParsingException {
        Stack<ExpressionNode> stack = new Stack<>();

        for (Token token : rpnTokens) {
            switch (token.getType()) {
                case NUMBER:
                    double value;
                    try {
                        value = Double.parseDouble(token.getValue());
                    } catch (NumberFormatException e) {
                        throw new ParsingException("Invalid number format: " + token.getValue());
                    }
                    stack.push(new NumberNode(value));
                    break;

                case CONSTANT:
                    double constValue = ConstantFactory.getConstantValue(token.getValue());
                    stack.push(new NumberNode(constValue));
                    break;

                case MODE:
                    ModeFactory.applyMode(token.getValue(), CalculatorContext.getInstance());
                    break;

                case FUNCTION:
                case OPERATOR:
                    Operation operation = OperationFactory.getOperation(token.getValue());
                    if (operation == null) {
                        throw new ParsingException("Unknown operation: " + token.getValue());
                    }
                    int operandCount = operation.getOperandCount();
                    if (stack.size() < operandCount) {
                        throw new ParsingException("Not enough operands for operation: " + token.getValue());
                    }
                    ExpressionNode[] operands = new ExpressionNode[operandCount];
                    for (int i = operandCount - 1; i >= 0; i--) {
                        operands[i] = stack.pop();
                    }
                    stack.push(new OperationNode(operation, operands));
                    break;

                default:
                    throw new ParsingException("Unexpected token: " + token.getValue());
            }
        }

        if (stack.size() != 1) {
            throw new ParsingException("Invalid expression");
        }

        return stack.pop();
    }

    private boolean isUnaryOperator(Token currentToken, Token previousToken) {
        if (currentToken.getType() != Token.Type.OPERATOR) {
            return false;
        }

        String symbol = currentToken.getValue();

        if (previousToken == null) {
            return true;
        }

        return switch (previousToken.getType()) {
            case OPERATOR, FUNCTION, LEFT_PAREN, MODE -> true;
            default -> false;
        };
    }
}