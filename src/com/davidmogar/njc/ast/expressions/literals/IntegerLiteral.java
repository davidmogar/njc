package com.davidmogar.njc.ast.expressions.literals;

import com.davidmogar.njc.ast.AbstractAstNode;

import java.lang.*;

public class IntegerLiteral extends AbstractAstNode implements Number {

    public int value;

    public IntegerLiteral(int line, int column, int value) {
        super(line, column);
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
