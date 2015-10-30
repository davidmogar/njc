package com.davidmogar.njc.ast.expressions.operators.unary;

import com.davidmogar.njc.Visitor;
import com.davidmogar.njc.ast.expressions.Expression;
import com.davidmogar.njc.ast.types.Type;

public class CastOperator extends UnaryOperator {

    public Type type;

    public CastOperator(int line, int column, Expression expression, Type type) {
        super(line, column, expression);
        this.type = type;
    }

    @Override
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }

}
