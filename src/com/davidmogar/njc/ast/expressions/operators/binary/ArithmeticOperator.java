package com.davidmogar.njc.ast.expressions.operators.binary;

import com.davidmogar.njc.visitors.Visitor;
import com.davidmogar.njc.ast.expressions.Expression;

public class ArithmeticOperator extends BinaryOperator {

    public ArithmeticOperator(int line, int column, Expression leftExpression, Expression rightExpression, String operator) {
        super(line, column, leftExpression, rightExpression, operator);
    }

    @Override
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }

}
