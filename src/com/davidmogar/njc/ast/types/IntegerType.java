package com.davidmogar.njc.ast.types;

import com.davidmogar.njc.ast.AbstractAstNode;

public class IntegerType extends AbstractAstNode implements Type {

    private static IntegerType instance;

    private IntegerType(int line, int column) {
        super(line, column);
    }

    public IntegerType getInstance(int line, int column) {
        if (instance == null) {
            instance = new IntegerType(line, column);
        }

        return instance;
    }

}
