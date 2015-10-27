package com.davidmogar.njc.ast.expressions;

import com.davidmogar.njc.ast.AbstractAstNode;

public class Variable extends AbstractAstNode implements Expression {

    public String name;

    public Variable(int line, int column, String name) {
        super(line, column);
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}