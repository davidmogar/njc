package com.davidmogar.njc.ast.statements;

import com.davidmogar.njc.visitors.Visitor;
import com.davidmogar.njc.ast.AbstractAstNode;
import com.davidmogar.njc.ast.expressions.Expression;

public class AssignmentStatement extends AbstractAstNode implements Statement {

    public Expression leftExpression;
    public Expression rightExpression;

    public AssignmentStatement(int line, int column, Expression leftExpression, Expression rightExpression) {
        super(line, column);
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }


}
