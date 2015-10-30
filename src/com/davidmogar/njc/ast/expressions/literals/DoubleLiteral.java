package com.davidmogar.njc.ast.expressions.literals;

import com.davidmogar.njc.ast.expressions.AbstractExpression;
import com.davidmogar.njc.visitors.Visitor;

public class DoubleLiteral extends AbstractExpression {

    public double value;

    public DoubleLiteral(int line, int column, double value) {
        super(line, column);
        this.value = value;
    }

    @Override
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }

}
