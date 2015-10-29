package com.davidmogar.njc.ast.expressions.operators.binary;

import com.davidmogar.njc.ast.AbstractAstNode;
import com.davidmogar.njc.ast.expressions.Expression;

public abstract class BinaryOperator extends AbstractAstNode implements Expression {

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
