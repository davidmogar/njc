package com.davidmogar.njc.ast;

public abstract class AbstractAstNode implements AstNode {

    private int column;
    private int line;

    public AbstractAstNode(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    public int getLine() {
        return line;
    }

}
