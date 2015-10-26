package com.davidmogar.njc;

public abstract class AbstractAstNode implements AstNode {

    public int line;
    public int column;

    public AbstractAstNode(int line, int column) {
        this.line = line;
        this.column = column;
    }

}
