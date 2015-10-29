package com.davidmogar.njc.ast.definitions;

import com.davidmogar.njc.ast.AbstractAstNode;
import com.davidmogar.njc.ast.types.Type;

public abstract class Definition extends AbstractAstNode {

    private String name;
    private Type type;

    public Definition(int line, int column, String name, Type type) {
        super(line, column);
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

}
