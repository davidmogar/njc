package com.davidmogar.njc.ast.expressions;

import com.davidmogar.njc.visitors.Visitor;

public class Variable extends AbstractExpression {

    public String name;

    public Variable(int line, int column, String name) {
        super(line, column);
        this.name = name;

        setLeftValue(true);
    }

    @Override
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }

}
