package com.davidmogar.njc.ast.expressions.operators.unary;

import com.davidmogar.njc.Visitor;
import com.davidmogar.njc.ast.expressions.Expression;

public class NegationOperator extends UnaryOperator {

    public NegationOperator(int line, int column, Expression expression) {
        super(line, column, expression);
    }

    @Override
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }

}
