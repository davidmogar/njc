package com.davidmogar.njc.ast.expressions.literals;

import com.davidmogar.njc.ast.expressions.AbstractExpression;
import com.davidmogar.njc.ast.types.DoubleType;
import com.davidmogar.njc.visitors.Visitor;

public class DoubleLiteral extends AbstractExpression {

    public double value;

    public DoubleLiteral(int line, int column, double value) {
        super(line, column, DoubleType.getInstance(line, column));
        this.value = value;
    }

    @Override
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }
}
