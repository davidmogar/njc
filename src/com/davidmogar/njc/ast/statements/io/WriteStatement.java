package com.davidmogar.njc.ast.statements.io;

import com.davidmogar.njc.ast.AbstractAstNode;
import com.davidmogar.njc.ast.expressions.Expression;
import com.davidmogar.njc.ast.statements.Statement;

import java.util.List;

public class WriteStatement extends AbstractAstNode implements Statement {

    public List<Expression> expressions;

    public WriteStatement(int line, int column, List<Expression> expressions) {
        super(line, column);
        this.expressions = expressions;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("write ");
        for (Expression expression : expressions) {
            builder.append(expression);
            builder.append(", ");
        }
        builder.setLength(builder.length() - 2);
        return builder.toString();
    }
}
