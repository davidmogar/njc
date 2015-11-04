package com.davidmogar.njc.ast.expressions.operators.binary;

import com.davidmogar.njc.visitors.Visitor;
import com.davidmogar.njc.ast.expressions.Expression;

public class LogicalOperator extends BinaryOperator {

    public LogicalOperator(int line, int column, Expression leftExpression, Expression rightExpression, String operator) {
        super(line, column, leftExpression, rightExpression, operator);
    }

    @Override
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }

}