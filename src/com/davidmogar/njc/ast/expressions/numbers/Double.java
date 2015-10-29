package com.davidmogar.njc.ast.expressions.numbers;

import com.davidmogar.njc.ast.AbstractAstNode;

public class Double extends AbstractAstNode implements Number {

    public int value;

    public Double(int line, int column, int value) {
        super(line, column);
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
