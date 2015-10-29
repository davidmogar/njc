package com.davidmogar.njc.ast.statements;

import com.davidmogar.njc.ast.AbstractAstNode;
import com.davidmogar.njc.ast.statements.Statement;

import java.util.List;

public abstract class Block extends AbstractAstNode implements Statement {

    public List<Statement> statements;

    public Block(int line, int column, List<Statement> statements) {
        super(line, column);
        this.statements = statements;
    }

}
