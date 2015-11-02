package com.davidmogar.njc;

import com.davidmogar.njc.ast.AstNode;

public class TypeError {

    private String message;

    private int column;
    private int line;

    public TypeError(int line, int column, String message) {
        this.line = line;
        this.column = column;
        this.message = message;

        ErrorHandler.getInstance().addTypeError(this);
    }

    public TypeError(AstNode node, String message) {
        this(node.getLine(), node.getColumn(), message);
    }

    @Override
    public String toString() {
        return "(" + line + ", " + column + ") " + message;
    }
}
