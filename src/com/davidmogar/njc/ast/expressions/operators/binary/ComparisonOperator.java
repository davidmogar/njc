package com.davidmogar.njc.ast.expressions.operators.binary;

import com.davidmogar.njc.ast.expressions.Expression;

public class ComparisonOperator extends BinaryOperator {

    public ComparisonOperator(int line, int column, Expression leftExpression, Expression rightExpression, String operator) {
        super(line, column, leftExpression, rightExpression, operator);
    }

}
