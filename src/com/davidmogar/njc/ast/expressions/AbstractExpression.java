package com.davidmogar.njc.ast.expressions;

import com.davidmogar.njc.ast.AbstractAstNode;
import com.davidmogar.njc.ast.types.Type;

public abstract class AbstractExpression extends AbstractAstNode implements Expression {

    private boolean leftValue;
    private Type type;

    public AbstractExpression(int line, int column) {
        this(line, column, null);
    }

    public AbstractExpression(int line, int column, Type type) {
        super(line, column);
        this.type = type;

        leftValue = false;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public boolean isLeftValue() {
        return leftValue;
    }

    @Override
    public void setLeftValue(boolean leftValue) {
        this.leftValue = leftValue;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }
}
