package com.davidmogar.njc.ast.expressions.literals;

import com.davidmogar.njc.ast.AbstractAstNode;

public class CharacterLiteral extends AbstractAstNode implements Number {

    public char value;

    public CharacterLiteral(int line, int column, char value) {
        super(line, column);
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}