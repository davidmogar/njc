package com.davidmogar.njc.ast.types;

import com.davidmogar.njc.Visitor;
import com.davidmogar.njc.ast.AbstractAstNode;

public class StringType extends AbstractAstNode implements Type {

    private static StringType instance;

    private StringType(int line, int column) {
        super(line, column);
    }

    public static StringType getInstance(int line, int column) {
        if (instance == null) {
            instance = new StringType(line, column);
        }

        return instance;
    }

    @Override
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }
    
}
