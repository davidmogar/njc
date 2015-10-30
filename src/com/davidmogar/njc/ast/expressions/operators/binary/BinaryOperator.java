package com.davidmogar.njc.ast.expressions.operators.binary;

import com.davidmogar.njc.ast.expressions.AbstractExpression;
import com.davidmogar.njc.ast.expressions.Expression;

public abstract class BinaryOperator extends AbstractExpression {

    public Expression leftExpression;
    public Expression rightExpression;
    public String operator;

    public BinaryOperator(int line, int column, Expression leftExpression, Expression rightExpression, String operator) {
        super(line, column);
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.operator = operator;
    }

}
