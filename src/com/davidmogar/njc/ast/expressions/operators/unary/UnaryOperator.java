package com.davidmogar.njc.ast.expressions.operators.unary;

import com.davidmogar.njc.Visitor;
import com.davidmogar.njc.ast.AbstractAstNode;
import com.davidmogar.njc.ast.expressions.Expression;

public abstract class UnaryOperator extends AbstractAstNode implements Expression {

    public Expression expression;

    public UnaryOperator(int line, int column, Expression expression) {
        super(line, column);
        this.expression = expression;
    }

    @Override
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }

}
