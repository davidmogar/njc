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
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }

}
