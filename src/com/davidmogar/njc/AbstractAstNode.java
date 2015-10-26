package com.davidmogar.njc;

public abstract class AbstractAstNode implements AstNode {

    private int column;
    private int line;

    public AbstractAstNode(int line, int column) {
        this.line = line;
        this.column = column;
        int a = 0;
        a++;
    }

    public int getColumn() {
        return column;
    }

    public int getLine() {
        return line;
    }

}
