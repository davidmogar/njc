package com.davidmogar.njc.expressions.operators;

import com.davidmogar.njc.AbstractAstNode;
import com.davidmogar.njc.expressions.Expression;

public class ArithmeticOperator extends AbstractAstNode implements Operator {

    public Expression leftValue;
    public Expression rightValue;
    public String operator;

    public ArithmeticOperator(int line, int column, Expression leftValue, Expression rightValue, String operator) {
        super(line, column);
        this.leftValue = leftValue;
        this.rightValue = rightValue;
        this.operator = operator;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(leftValue);
        builder.append(operator);
        builder.append(rightValue);
        return builder.toString();
    }

}
