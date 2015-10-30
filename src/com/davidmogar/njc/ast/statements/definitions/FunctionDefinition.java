package com.davidmogar.njc.ast.statements.definitions;

import com.davidmogar.njc.visitors.Visitor;
import com.davidmogar.njc.ast.statements.Block;
import com.davidmogar.njc.ast.statements.Statement;
import com.davidmogar.njc.ast.types.Type;

public class FunctionDefinition extends AbstractDefinition implements Statement {

    public Block block;

    public FunctionDefinition(int line, int column, String name, Type type, Block block) {
        super(line, column, name, type);
        this.block = block;
    }

    @Override
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }

}
