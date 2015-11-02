package com.davidmogar.njc.ast.statements.definitions;

import com.davidmogar.njc.ast.AstNode;
import com.davidmogar.njc.ast.statements.Statement;
import com.davidmogar.njc.ast.types.Type;

public interface Definition extends AstNode, Statement {

    String getName();

    Type getType();

}
