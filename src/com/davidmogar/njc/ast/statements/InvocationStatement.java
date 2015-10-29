package com.davidmogar.njc.ast.statements;

import com.davidmogar.njc.ast.AbstractAstNode;
import com.davidmogar.njc.ast.expressions.Expression;
import com.davidmogar.njc.ast.expressions.Variable;

import java.util.ArrayList;
import java.util.List;

public class InvocationStatement extends AbstractAstNode implements Statement {

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

}
