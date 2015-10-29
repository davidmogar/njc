package com.davidmogar.njc.ast.statements.definitions;

import com.davidmogar.njc.ast.types.Type;

public class VariableDefinition extends AbstractDefinition {

    public int offset;
    public int scope;

    public VariableDefinition(int line, int column, String name, Type type) {
        super(line, column, name, type);
    }

}
