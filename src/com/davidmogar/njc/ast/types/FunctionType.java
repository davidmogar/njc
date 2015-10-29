package com.davidmogar.njc.ast.types;

import com.davidmogar.njc.ast.AbstractAstNode;

import java.util.List;

public class FunctionType extends AbstractAstNode implements Type {

    public List<Type> parameters;
    public Type returnType;

    public FunctionType(int line, int column, List<Type> parameters, Type returnType) {
        super(line, column);
        this.parameters = parameters;
        this.returnType = returnType;
    }
}
