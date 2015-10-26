package com.davidmogar.njc.statements;

import com.davidmogar.njc.AbstractAstNode;
import com.davidmogar.njc.expressions.Expression;

public class AssignmentStatement extends AbstractAstNode implements Statement {

    public Expression leftValue;
    public Expression rightValue;

    public AssignmentStatement(int line, int column, Expression leftValue, Expression rightValue) {
        super(line, column);
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }

    @Override
    public String toString() {
        return leftValue + " = " + rightValue;
    }
}
