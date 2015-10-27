package com.davidmogar.njc.ast.expressions.operators;

import com.davidmogar.njc.ast.AbstractAstNode;
import com.davidmogar.njc.ast.expressions.Expression;

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
