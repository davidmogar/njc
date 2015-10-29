package com.davidmogar.njc.ast.types;

import com.davidmogar.njc.ast.AbstractAstNode;
import com.davidmogar.njc.ast.statements.definitions.VariableDefinition;

import java.util.ArrayList;
import java.util.List;

public class FunctionType extends AbstractAstNode implements Type {

    public List<VariableDefinition> parameters;
    public Type returnType;

    public FunctionType(int line, int column, Type returnType) {
        this(line, column, new ArrayList<>(), returnType);
    }

    public FunctionType(int line, int column, List<VariableDefinition> parameters, Type returnType) {
        super(line, column);
        this.parameters = parameters;
        this.returnType = returnType;
    }
}
