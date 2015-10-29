package com.davidmogar.njc.ast.definitions;

import com.davidmogar.njc.ast.statements.Block;
import com.davidmogar.njc.ast.types.Type;

public class FunctionDefinition extends Definition {

    public Block block;

    public FunctionDefinition(int line, int column, String name, Type type, Block block) {
        super(line, column, name, type);
    }

}
