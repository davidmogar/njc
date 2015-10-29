package com.davidmogar.njc.ast.expressions.operators.binary;

import com.davidmogar.njc.ast.AbstractAstNode;
import com.davidmogar.njc.ast.expressions.Expression;

public abstract class UnaryOperator extends AbstractAstNode implements Expression {

    public Expression value;

    public UnaryOperator(int line, int column, Expression value) {
        super(line, column);
        this.value = value;
    }

}
