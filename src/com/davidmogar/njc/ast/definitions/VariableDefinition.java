package com.davidmogar.njc.ast.definitions;

import com.davidmogar.njc.ast.types.Type;

public class VariableDefinition extends Definition {

    public VariableDefinition(int line, int column, String name, Type type) {
        super(line, column, name, type);
    }

}
