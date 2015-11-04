package com.davidmogar.njc.ast.types;

import com.davidmogar.njc.ast.expressions.Expression;
import com.davidmogar.njc.visitors.Visitor;
import com.davidmogar.njc.ast.AbstractAstNode;
import com.davidmogar.njc.ast.statements.definitions.VariableDefinition;

import java.util.ArrayList;
import java.util.List;

public class FunctionType extends AbstractType implements Type {

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

    @Override
    public Type inferInvocationReturnType(List<Expression> expressions) {
        int parametersSize = parameters.size();
        boolean valid = true;

        if (parametersSize == expressions.size()) {
            for (int i = 0; i < parametersSize; i++) {
                if (!expressions.get(i).getType().isPromotable(parameters.get(i).getType())) {
                    valid = false;
                    break;
                }
            }
        } else {
            valid = false;
        }

        return valid? returnType : null;
    }

    @Override
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }

}
