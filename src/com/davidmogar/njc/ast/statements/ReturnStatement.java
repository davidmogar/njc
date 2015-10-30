package com.davidmogar.njc.ast.statements;

import com.davidmogar.njc.visitors.Visitor;
import com.davidmogar.njc.ast.AbstractAstNode;
import com.davidmogar.njc.ast.expressions.Expression;

public class ReturnStatement extends AbstractAstNode implements Statement {

    public Expression expression;

    public ReturnStatement(int line, int column, Expression expression) {
        super(line, column);
        this.expression = expression;
    }

    @Override
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }

}
