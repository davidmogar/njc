package com.davidmogar.njc.ast.expressions;

import com.davidmogar.njc.ast.AbstractAstNode;

public abstract class AbstractExpression extends AbstractAstNode implements Expression {

    private boolean leftValue;

    public AbstractExpression(int line, int column) {
        super(line, column);

        leftValue = false;
    }

    @Override
    public boolean isLeftValue() {
        return leftValue;
    }

    @Override
    public void setLeftValue(boolean leftValue) {
        this.leftValue = leftValue;
    }

}
