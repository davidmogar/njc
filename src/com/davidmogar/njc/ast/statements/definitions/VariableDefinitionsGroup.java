package com.davidmogar.njc.ast.statements.definitions;

import com.davidmogar.njc.ast.AbstractAstNode;
import com.davidmogar.njc.ast.statements.Statement;
import com.davidmogar.njc.visitors.Visitor;

import java.util.List;

public class VariableDefinitionsGroup extends AbstractAstNode implements Statement {

    public List<VariableDefinition> variableDefinitions;

    public VariableDefinitionsGroup(int line, int column, List<VariableDefinition> variableDefinitions) {
        super(line, column);
        this.variableDefinitions = variableDefinitions;
    }

    public void merge(VariableDefinitionsGroup variableDefinitionsGroup) {
        variableDefinitions.addAll(variableDefinitionsGroup.variableDefinitions);
    }

    @Override
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }

}
