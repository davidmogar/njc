package com.davidmogar.njc;

public class DecrementUnaryOperator extends AbstractAstNode implements UnaryOperator {

    public Expression expression;

    public DecrementUnaryOperator(int line, int column, Expression expression) {
        super(line, column);
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "-" + expression;
    }
}
