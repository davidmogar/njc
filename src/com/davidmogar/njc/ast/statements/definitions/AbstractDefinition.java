package com.davidmogar.njc.ast.statements.definitions;

import com.davidmogar.njc.ast.AbstractAstNode;
import com.davidmogar.njc.ast.types.Type;

public abstract class AbstractDefinition extends AbstractAstNode implements Definition {

    private String name;
    private Type type;

    public AbstractDefinition(int line, int column, String name, Type type) {
        super(line, column);
        this.name = name;
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Type getType() {
        return type;
    }

}
