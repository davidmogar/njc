package com.davidmogar.njc;

import java.util.List;

public abstract class Block extends AbstractAstNode {

    public List<Statement> statements;

    public Block(int line, int column, List<Statement> statements) {
        super(line, column);
        this.statements = statements;
    }

}
