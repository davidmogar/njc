package com.davidmogar.njc.ast.expressions.operators.unary;

import com.davidmogar.njc.ast.AbstractAstNode;
import com.davidmogar.njc.ast.expressions.Expression;

public class NegationOperator extends UnaryOperator {

    public Expression expression;

    public NegationOperator(int line, int column, Expression expression) {
        super(line, column, expression);
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "-" + expression;
    }
}
