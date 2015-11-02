package com.davidmogar.njc.semantic;

import com.davidmogar.njc.TypeError;
import com.davidmogar.njc.ast.expressions.Expression;
import com.davidmogar.njc.ast.statements.AssignmentStatement;
import com.davidmogar.njc.ast.statements.io.ReadStatement;
import com.davidmogar.njc.visitors.AbstractVisitor;

public class SemanticVisitor extends AbstractVisitor {

    @Override
    public Object visit(AssignmentStatement assignmentStatement, Object object) {
        super.visit(assignmentStatement, object);

        if (!assignmentStatement.leftValue.isLeftValue()) {
            new TypeError(assignmentStatement.leftValue, "Not assignable expression found on left side");
        }

        return null;
    }

    @Override
    public Object visit(ReadStatement readStatement, Object object) {
        super.visit(readStatement, object);

        for (Expression expression : readStatement.expressions) {
            if (!expression.isLeftValue()) {
                new TypeError(expression, "Not assignable expression found on left side");
            }
        }

        return null;
    }

}
