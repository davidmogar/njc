package com.davidmogar.njc;

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
