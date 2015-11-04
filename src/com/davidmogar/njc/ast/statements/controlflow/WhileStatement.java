package com.davidmogar.njc.ast.statements.controlflow;

import com.davidmogar.njc.visitors.Visitor;
import com.davidmogar.njc.ast.AbstractAstNode;
import com.davidmogar.njc.ast.expressions.Expression;
import com.davidmogar.njc.ast.statements.Block;
import com.davidmogar.njc.ast.statements.Statement;

public class WhileStatement extends AbstractAstNode implements Statement {

    public Block block;
    public Expression condition;

    public WhileStatement(int line, int column, Expression condition, Block block) {
        super(line, column);
        this.condition = condition;
        this.block = block;
    }

    @Override
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }

}
