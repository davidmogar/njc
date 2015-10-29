package com.davidmogar.njc.ast.types;

import com.davidmogar.njc.ast.AbstractAstNode;

public class DoubleType extends AbstractAstNode implements Type {

    private static DoubleType instance;

    private DoubleType(int line, int column) {
        super(line, column);
    }

    public static DoubleType getInstance(int line, int column) {
        if (instance == null) {
            instance = new DoubleType(line, column);
        }

        return instance;
    }
    
}
