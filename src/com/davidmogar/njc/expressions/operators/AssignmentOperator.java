package com.davidmogar.njc.expressions.operators;

import com.davidmogar.njc.AbstractAstNode;
import com.davidmogar.njc.expressions.Expression;

public class AssignmentOperator extends AbstractAstNode implements Operator {

    public Expression leftValue;
    public Expression rightValue;

    public AssignmentOperator(int line, int column, Expression leftValue, Expression rightValue) {
        super(line, column);
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }

    @Override
    public String toString() {
        return leftValue + " = " + rightValue;
    }
}
