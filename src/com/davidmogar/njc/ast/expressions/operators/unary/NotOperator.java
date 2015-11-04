package com.davidmogar.njc.ast.expressions.operators.unary;

import com.davidmogar.njc.visitors.Visitor;
import com.davidmogar.njc.ast.expressions.Expression;

public class NotOperator extends UnaryOperator {

    public NotOperator(int line, int column, Expression expression) {
        super(line, column, expression);
    }

    @Override
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }

}
