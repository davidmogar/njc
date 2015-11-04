package com.davidmogar.njc.ast.statements.io;

import com.davidmogar.njc.visitors.Visitor;
import com.davidmogar.njc.ast.AbstractAstNode;
import com.davidmogar.njc.ast.expressions.Expression;
import com.davidmogar.njc.ast.statements.Statement;

import java.util.List;

public class ReadStatement extends AbstractAstNode implements Statement {

    public List<Expression> expressions;

    public ReadStatement(int line, int column, List<Expression> expressions) {
        super(line, column);
        this.expressions = expressions;
    }

    @Override
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }

}
