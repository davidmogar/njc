package com.davidmogar.njc.ast.expressions.operators.binary;

import com.davidmogar.njc.ast.expressions.Expression;

public class LogicalOperator extends BinaryOperator {

    public LogicalOperator(int line, int column, Expression leftValue, Expression rightValue, String operator) {
        super(line, column, leftValue, rightValue, operator);
    }

}
