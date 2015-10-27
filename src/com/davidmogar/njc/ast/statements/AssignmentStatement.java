package com.davidmogar.njc.ast.statements;

import com.davidmogar.njc.ast.AbstractAstNode;
import com.davidmogar.njc.ast.expressions.Expression;

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
