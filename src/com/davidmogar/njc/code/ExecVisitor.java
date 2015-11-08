package com.davidmogar.njc.code;

import com.davidmogar.njc.ast.Program;
import com.davidmogar.njc.ast.expressions.Expression;
import com.davidmogar.njc.ast.statements.*;
import com.davidmogar.njc.ast.statements.controlflow.IfStatement;
import com.davidmogar.njc.ast.statements.controlflow.WhileStatement;
import com.davidmogar.njc.ast.statements.definitions.Definition;
import com.davidmogar.njc.ast.statements.definitions.FunctionDefinition;
import com.davidmogar.njc.ast.statements.definitions.VariableDefinition;
import com.davidmogar.njc.ast.statements.definitions.VariableDefinitionsGroup;
import com.davidmogar.njc.ast.statements.io.ReadStatement;
import com.davidmogar.njc.ast.statements.io.WriteStatement;
import com.davidmogar.njc.ast.types.FunctionType;
import com.davidmogar.njc.ast.types.IntegerType;
import com.davidmogar.njc.ast.types.Type;
import com.davidmogar.njc.ast.types.VoidType;

import java.io.File;
import java.io.FileNotFoundException;

public class ExecVisitor extends AbstractCodeVisitor {

    private AddressVisitor addressVisitor;
    private CodeGenerator codeGenerator;
    private ValueVisitor valueVisitor;

    private int tags = 0;

    public ExecVisitor(File inputFile, File outputFile) throws FileNotFoundException {
        codeGenerator = new CodeGenerator(inputFile, outputFile);
        addressVisitor = new AddressVisitor(codeGenerator);
        valueVisitor = new ValueVisitor(codeGenerator, this);

        addressVisitor.setValueVisitor(valueVisitor);
        valueVisitor.setAddressVisitor(addressVisitor);
    }

    @Override
    public Object visit(IfStatement ifStatement, Object object) {
        int tag = tags;
        tags += 2;

        ifStatement.condition.accept(valueVisitor, object);
        codeGenerator.cast(ifStatement.condition.getType(), IntegerType.getInstance());
        codeGenerator.jz("tag" + tag);
        ifStatement.ifBlock.accept(this, object);
        codeGenerator.jmp("tag" + (tag + 1));
        codeGenerator.tag("tag" + tag++);
        ifStatement.elseBlock.accept(this, object);
        codeGenerator.tag("tag" + tag++);

        return null;
    }

    @Override
    public Object visit(WhileStatement whileStatement, Object object) {
        int tag = tags;
        tags += 2;

        codeGenerator.tag("tag" + tag++);
        whileStatement.condition.accept(valueVisitor, object);
        codeGenerator.cast(whileStatement.condition.getType(), IntegerType.getInstance());
        codeGenerator.jz("tag" + tag);
        whileStatement.block.accept(this, object);
        codeGenerator.jmp("tag" + (tag - 1));
        codeGenerator.tag("tag" + tag++);

        return null;
    }

    @Override
    public Object visit(FunctionDefinition functionDefinition, Object object) {
        codeGenerator.tag(functionDefinition.getName());
        codeGenerator.enter(functionDefinition.getOffset());

        FunctionType functionType = (FunctionType) functionDefinition.getType();
        for (VariableDefinition variableDefinition : functionType.parameters) {
            variableDefinition.accept(this, object);
        }

        functionDefinition.block.accept(this, functionDefinition);

        if (functionType.returnType instanceof VoidType) {
            codeGenerator.ret(0, functionDefinition.getOffset(), functionType.parametersOffset);
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
        for (Expression expression : writeStatement.expressions) {
            codeGenerator.comment("Writing");
            expression.accept(valueVisitor, object);
            codeGenerator.out(expression.getType());
        }

        return null;
    }

    @Override
    public Object visit(AssignmentStatement assignmentStatement, Object object) {
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
    public Object visit(InvocationStatement invocationStatement, Object object) {
        invocationStatement.accept(valueVisitor, object);

        FunctionType functionType = (FunctionType) invocationStatement.variable.definition.getType();
        if (!(functionType.returnType instanceof VoidType)) {
            codeGenerator.pop(functionType.returnType);
        }

        return null;
    }

    @Override
    public Object visit(ReturnStatement returnStatement, Object object) {
        FunctionDefinition functionDefinition = (FunctionDefinition) object;
        returnStatement.expression.accept(valueVisitor, object);
        codeGenerator.ret(returnStatement.expression.getType().getSize(), functionDefinition.getOffset(),
                ((FunctionType) functionDefinition.getType()).parametersOffset);

        return null;
    }

    @Override
    public Object visit(Program program, Object object) {
        codeGenerator.entryPoint();
        codeGenerator.breakline();

        for (Definition definition : program.definitions) {
            if (definition instanceof FunctionDefinition) {
                definition.accept(this, object);
            }
        }

        return null;
    }

}
