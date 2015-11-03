package com.davidmogar.njc;

import com.davidmogar.njc.ast.AstNode;
import com.davidmogar.njc.ast.types.AbstractType;
import com.davidmogar.njc.visitors.Visitor;

public class TypeError extends AbstractType {

    private String message;

    public TypeError(int line, int column, String message) {
        super(line, column);
        this.message = message;

        ErrorHandler.getInstance().addTypeError(this);
    }

    public TypeError(AstNode node, String message) {
        this(node.getLine(), node.getColumn(), message);
    }

    @Override
    public void accept(Visitor visitor, Object object) {
    }

    @Override
    public String getName() {
        return "TypeError";
    }

    @Override
    public String toString() {
        return "(" + getLine() + ", " + getColumn() + ") " + message;
    }
}
