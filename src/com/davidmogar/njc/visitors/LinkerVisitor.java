package com.davidmogar.njc.visitors;

import com.davidmogar.njc.TypeError;
import com.davidmogar.njc.ast.expressions.Variable;
import com.davidmogar.njc.ast.statements.Block;
import com.davidmogar.njc.ast.statements.Statement;
import com.davidmogar.njc.ast.statements.definitions.Definition;
import com.davidmogar.njc.ast.statements.definitions.FunctionDefinition;
import com.davidmogar.njc.ast.statements.definitions.VariableDefinition;
import com.davidmogar.njc.semantic.SymbolsTable;

public class LinkerVisitor extends AbstractVisitor {

    private SymbolsTable symbolsTable;

    public LinkerVisitor() {
        symbolsTable = new SymbolsTable();
    }

    @Override
    public Object visit(Variable variable, Object object) {
        Definition definition = symbolsTable.getDefinition(variable.name);
        if (definition != null) {
            variable.definition = definition;
            variable.setType(variable.definition.getType());
        } else {
            new TypeError(variable, "Cannot resolve symbol '" + variable.name + "'");
        }

        return super.visit(variable, object);
    }

    @Override
    public Object visit(Block block, Object object) {
        symbolsTable.createScope();

        for (Statement statement : block.statements) {
            statement.accept(this, object);
        }

        symbolsTable.removeScope();
        return null;
    }

    @Override
    public Object visit(FunctionDefinition functionDefinition, Object object) {
        if (!symbolsTable.addDefinition(functionDefinition)) {
            new TypeError(functionDefinition, "Function '" + functionDefinition.getName() + "' is already defined");
        }
        return super.visit(functionDefinition, object);
    }

    @Override
    public Object visit(VariableDefinition variableDefinition, Object object) {
        if (!symbolsTable.addDefinition(variableDefinition)) {
            new TypeError(variableDefinition, "Variable '" + variableDefinition.getName() +
                    "' is already defined in the scope");
        }
        return super.visit(variableDefinition, object);
    }

}
