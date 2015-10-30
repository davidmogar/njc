package com.davidmogar.njc.ast.statements;

import com.davidmogar.njc.Visitor;
import com.davidmogar.njc.ast.AbstractAstNode;
import com.davidmogar.njc.ast.statements.Statement;

import java.util.ArrayList;
import java.util.List;

public class Block extends AbstractAstNode implements Statement {

    public List<Statement> statements;

    public Block(int line, int column) {
        this(line, column, new ArrayList<>());
    }

    public Block(int line, int column, List<Statement> statements) {
        super(line, column);
        this.statements = statements;
    }

    @Override
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }

}
