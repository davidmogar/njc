package com.davidmogar.njc.expressions.operators;

import com.davidmogar.njc.AbstractAstNode;
import com.davidmogar.njc.expressions.Expression;
import com.davidmogar.njc.expressions.operators.UnaryOperator;

public class NegationOperator extends AbstractAstNode implements UnaryOperator {

    public Expression expression;

    public NegationOperator(int line, int column, Expression expression) {
        super(line, column);
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "-" + expression;
    }
}
