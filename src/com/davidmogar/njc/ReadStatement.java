package com.davidmogar.njc;

import java.util.List;

public class ReadStatement extends AbstractAstNode implements Statement {

    public List<Expression> expressions;

    public ReadStatement(int line, int column, List<Expression> expressions) {
        super(line, column);
        this.expressions = expressions;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("read ");
        for (Expression expression : expressions) {
            builder.append(expression);
            builder.append(", ");
        }
        builder.setLength(builder.length() - 2);
        return builder.toString();
    }
}
