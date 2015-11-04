package com.davidmogar.njc.ast.expressions.operators.binary;

import com.davidmogar.njc.visitors.Visitor;
import com.davidmogar.njc.ast.expressions.Expression;

public class ArrayAccessOperator extends BinaryOperator {

    public ArrayAccessOperator(int line, int column, Expression leftExpression, Expression rightExpression) {
        super(line, column, leftExpression, rightExpression, "[]");

        setLeftValue(true);
    }

    @Override
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }
}
