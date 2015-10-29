package com.davidmogar.njc.ast.expressions.operators.binary;

import com.davidmogar.njc.ast.AbstractAstNode;
import com.davidmogar.njc.ast.expressions.Expression;

public abstract class BinaryOperator extends AbstractAstNode implements Expression {

    public Expression leftValue;
    public Expression rightValue;
    public String operator;

    public BinaryOperator(int line, int column, Expression leftValue, Expression rightValue, String operator) {
        super(line, column);
        this.leftValue = leftValue;
        this.rightValue = rightValue;
        this.operator = operator;
    }

}
