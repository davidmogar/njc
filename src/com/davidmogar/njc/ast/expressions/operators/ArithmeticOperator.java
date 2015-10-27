package com.davidmogar.njc.ast.expressions.operators;

import com.davidmogar.njc.ast.AbstractAstNode;
import com.davidmogar.njc.ast.expressions.Expression;

public class ArithmeticOperator extends AbstractAstNode implements Operator {

    public Expression leftValue;
    public Expression rightValue;
    public String operator;

    public ArithmeticOperator(int line, int column, Expression leftValue, Expression rightValue, String operator) {
        super(line, column);
        this.leftValue = leftValue;
        this.rightValue = rightValue;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return leftValue + operator + rightValue;
    }

}
