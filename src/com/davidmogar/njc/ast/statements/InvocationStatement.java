package com.davidmogar.njc.ast.statements;

import com.davidmogar.njc.ast.expressions.AbstractExpression;
import com.davidmogar.njc.ast.expressions.Expression;
import com.davidmogar.njc.ast.expressions.Variable;
import com.davidmogar.njc.visitors.Visitor;

import java.util.ArrayList;
import java.util.List;

public class InvocationStatement extends AbstractExpression implements Statement {

    public List<Expression> arguments;
    public Variable variable;

    public InvocationStatement(int line, int column, Variable variable) {
        this(line, column, variable, new ArrayList<>());
    }

    public InvocationStatement(int line, int column, Variable variable, List<Expression> arguments) {
        super(line, column);
        this.variable = variable;
        this.arguments = arguments;
    }

    @Override
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(variable.name + "(");
        if (arguments.size() > 0) {
            for (Expression expression : arguments) {
                sb.append(expression.getType().getName());
                sb.append(", ");
            }
            sb.setLength(sb.length() - 2);
        }
        sb.append(")");
        return sb.toString();
    }
}
