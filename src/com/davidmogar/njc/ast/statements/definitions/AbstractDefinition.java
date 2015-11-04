package com.davidmogar.njc.ast.statements.definitions;

import com.davidmogar.njc.ast.AbstractAstNode;
import com.davidmogar.njc.ast.types.Type;

public abstract class AbstractDefinition extends AbstractAstNode implements Definition {

    private String name;
    private Type type;

    private int offset;
    private int scope;

    public AbstractDefinition(int line, int column, String name, Type type) {
        super(line, column);
        this.name = name;
        this.type = type;

        offset = 0;
        scope = 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getOffset() {
        return offset;
    }

    @Override
    public int getScope() {
        return scope;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public void setScope(int scope) {
        this.scope = scope;
    }

}
