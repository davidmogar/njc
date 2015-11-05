package com.davidmogar.njc.code;

import com.davidmogar.njc.ast.statements.Block;
import com.davidmogar.njc.ast.statements.Statement;
import com.davidmogar.njc.ast.statements.definitions.FunctionDefinition;
import com.davidmogar.njc.ast.statements.definitions.VariableDefinition;
import com.davidmogar.njc.ast.statements.definitions.VariableDefinitionsGroup;
import com.davidmogar.njc.ast.types.FunctionType;
import com.davidmogar.njc.visitors.AbstractVisitor;

public class OffsetVisitor extends AbstractVisitor {

    private int offsetSum;

    public OffsetVisitor() {
        offsetSum = 0;
    }

    @Override
    public Object visit(FunctionDefinition functionDefinition, Object object) {
        functionDefinition.getType().accept(this, object);
        functionDefinition.setOffset((Integer) functionDefinition.block.accept(this, object));

        return null;
    }


    @Override
    public Object visit(VariableDefinition variableDefinition, Object object) {
        super.visit(variableDefinition, object);

        Integer offset = null;

        /* Global variables */
        if (variableDefinition.getScope() == 0) {
            variableDefinition.setOffset(offsetSum);
            offsetSum += variableDefinition.getType().getSize();
        } else if (object != null) {
            offset = (Integer) object;
            offset -= variableDefinition.getType().getSize();
            variableDefinition.setOffset(offset);
        }

        return offset;
    }

    @Override
    public Object visit(VariableDefinitionsGroup variableDefinitionsGroup, Object object) {
        int blockOffsetSum;

        if (object != null) {
            blockOffsetSum = (Integer) object;
        } else {
            blockOffsetSum = 0;
        }

        for (VariableDefinition variableDefinition : variableDefinitionsGroup.variableDefinitions) {
            Object offset = variableDefinition.accept(this, blockOffsetSum);
            if (offset != null) {
                blockOffsetSum = (Integer) offset;
            }
        }
        return blockOffsetSum;
    }

    @Override
    public Object visit(Block block, Object object) {
        int blockOffsetSum = 0;

        for (Statement statement : block.statements) {
            Object offset = statement.accept(this, blockOffsetSum);
            if (offset != null) {
                blockOffsetSum = (Integer) offset;
            }
        }

        return -blockOffsetSum;
    }

    @Override
    public Object visit(FunctionType functionType, Object object) {
        int parametersOffsetSum = 4;

        for (VariableDefinition variableDefinition : functionType.parameters) {
            variableDefinition.setOffset(parametersOffsetSum);
            parametersOffsetSum += variableDefinition.getType().getSize();
        }

        return null;
    }
}
