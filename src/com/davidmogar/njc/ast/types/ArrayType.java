package com.davidmogar.njc.ast.types;

import com.davidmogar.njc.ast.AbstractAstNode;

public class ArrayType extends AbstractAstNode implements Type {

    private Type type;

    private int size;

    public ArrayType(int line, int column, Type type, int size) {
        super(line, column);
    }

    public Type getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

}
