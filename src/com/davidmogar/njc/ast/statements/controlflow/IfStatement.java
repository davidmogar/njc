package com.davidmogar.njc.ast.statements.controlflow;

import com.davidmogar.njc.Visitor;
import com.davidmogar.njc.ast.AbstractAstNode;
import com.davidmogar.njc.ast.expressions.Expression;
import com.davidmogar.njc.ast.statements.Block;
import com.davidmogar.njc.ast.statements.Statement;

public class IfStatement extends AbstractAstNode implements Statement {

    public Block elseBlock;
    public Block ifBlock;
    public Expression condition;

    public IfStatement(int line, int column, Expression condition, Block ifBlock) {
        this(line, column, condition, ifBlock, null);
    }

    public IfStatement(int line, int column, Expression condition, Block ifBlock, Block elseBlock) {
        super(line, column);
        this.condition = condition;
        this.ifBlock = ifBlock;
        this.elseBlock = elseBlock;
    }

    @Override
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }

}
