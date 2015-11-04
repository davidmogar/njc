package com.davidmogar.njc.ast.statements.definitions;

import com.davidmogar.njc.visitors.Visitor;
import com.davidmogar.njc.ast.types.Type;

public class VariableDefinition extends AbstractDefinition {

    public int offset;

    public VariableDefinition(int line, int column, String name, Type type) {
        super(line, column, name, type);
    }

    @Override
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }


}
