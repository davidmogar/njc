package com.davidmogar.njc.ast.statements;

import com.davidmogar.njc.ast.AbstractAstNode;
import com.davidmogar.njc.ast.statements.Statement;

import java.util.List;

public abstract class Block extends AbstractAstNode implements Statement {

    public Block block;

    public Block(int line, int column, Block block) {
        super(line, column);
        this.block = block;
    }

}
