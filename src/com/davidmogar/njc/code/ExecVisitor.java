package com.davidmogar.njc.code;

import com.davidmogar.njc.ast.Program;
import com.davidmogar.njc.ast.expressions.Expression;
import com.davidmogar.njc.ast.statements.AssignmentStatement;
import com.davidmogar.njc.ast.statements.Block;
import com.davidmogar.njc.ast.statements.Statement;
import com.davidmogar.njc.ast.statements.definitions.Definition;
import com.davidmogar.njc.ast.statements.definitions.FunctionDefinition;
import com.davidmogar.njc.ast.statements.definitions.VariableDefinition;
import com.davidmogar.njc.ast.statements.definitions.VariableDefinitionsGroup;
import com.davidmogar.njc.ast.statements.io.ReadStatement;
import com.davidmogar.njc.ast.statements.io.WriteStatement;
import com.davidmogar.njc.ast.types.FunctionType;
import com.davidmogar.njc.ast.types.Type;
import com.davidmogar.njc.ast.types.VoidType;

import java.io.FileNotFoundException;

public class ExecVisitor extends AbstractCodeVisitor {

    private AddressVisitor addressVisitor;
    private CodeGenerator codeGenerator;
    private ValueVisitor valueVisitor;

    public ExecVisitor(String input, String output) throws FileNotFoundException {
        codeGenerator = new CodeGenerator(input, output);
        addressVisitor = new AddressVisitor(codeGenerator);
        valueVisitor = new ValueVisitor(codeGenerator);

        addressVisitor.setValueVisitor(valueVisitor);
        valueVisitor.setAddressVisitor(addressVisitor);
    }

    @Override
    public Object visit(FunctionDefinition functionDefinition, Object object) {
        codeGenerator.line(functionDefinition.getLine());
        codeGenerator.tag(functionDefinition.getName());
        codeGenerator.enter(functionDefinition.getOffset());

        FunctionType functionType = (FunctionType) functionDefinition.getType();
        int parametersOffset = 0;
        for (VariableDefinition variableDefinition : functionType.parameters) {
            parametersOffset += variableDefinition.getType().getSize();
            variableDefinition.accept(this, object);
        }

        functionDefinition.block.accept(this, functionDefinition);

        if (functionType.returnType instanceof VoidType) {
            codeGenerator.ret(0, functionDefinition.getOffset(), parametersOffset);
        }

        return null;
    }

    @Override
    public Object visit(VariableDefinition variableDefinition, Object object) {
        if (variableDefinition.getScope() != 0) {
            codeGenerator.comment(variableDefinition.getType().getName() + " " + variableDefinition.getName() +
                    " (offset " + variableDefinition.getOffset() + ")");
        }

        return null;
    }

    @Override
    public Object visit(VariableDefinitionsGroup variableDefinitionsGroup, Object object) {
        for (VariableDefinition variableDefinition : variableDefinitionsGroup.variableDefinitions) {
            variableDefinition.accept(this, object);
        }

        return null;
    }

    @Override
    public Object visit(ReadStatement readStatement, Object object) {
        codeGenerator.line(readStatement.getLine()); // TODO: Move to block visitor

        for (Expression expression : readStatement.expressions) {
            codeGenerator.comment("Reading");
            expression.accept(addressVisitor, object);
            codeGenerator.in(expression.getType());
            codeGenerator.store(expression.getType());
        }

        return null;
    }

    @Override
    public Object visit(WriteStatement writeStatement, Object object) {
        codeGenerator.line(writeStatement.getLine());

        for (Expression expression : writeStatement.expressions) {
            codeGenerator.comment("Writing");
            expression.accept(valueVisitor, object);
            codeGenerator.out(expression.getType());
        }

        return null;
    }

    @Override
    public Object visit(AssignmentStatement assignmentStatement, Object object) {
        codeGenerator.line(assignmentStatement.getLine());

        assignmentStatement.leftExpression.accept(addressVisitor, object);
        assignmentStatement.rightExpression.accept(valueVisitor, object);

        Type leftType = assignmentStatement.leftExpression.getType();
        codeGenerator.cast(assignmentStatement.rightExpression.getType(), leftType);
        codeGenerator.store(leftType);

        return null;
    }

    @Override
    public Object visit(Block block, Object object) {
        for (Statement statement : block.statements) {
            codeGenerator.line(statement.getLine());
            statement.accept(this, object);
        }
        return null;
    }

    @Override
    public Object visit(Program program, Object object) {
        for (Definition definition : program.definitions) {
            if (definition instanceof FunctionDefinition) {
                if (definition.getName().equals("main")) {
                    codeGenerator.entryPoint();
                    codeGenerator.breakline();
                }
                definition.accept(this, object);
            }
        }

        return null;
    }
}
