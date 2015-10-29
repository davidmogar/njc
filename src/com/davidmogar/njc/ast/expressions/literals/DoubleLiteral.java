package com.davidmogar.njc.ast.expressions.literals;

import com.davidmogar.njc.ast.AbstractAstNode;

public class DoubleLiteral extends AbstractAstNode implements Number {

    public double value;

    public DoubleLiteral(int line, int column, double value) {
        super(line, column);
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
