package com.davidmogar.njc.ast.expressions.literals;

import com.davidmogar.njc.ast.AbstractAstNode;

public class StringLiteral extends AbstractAstNode implements Number {

    public String value;

    public StringLiteral(int line, int column, String value) {
        super(line, column);
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
