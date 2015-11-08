package com.davidmogar.njc.semantic;

import com.davidmogar.njc.TypeError;
import com.davidmogar.njc.ast.Program;
import com.davidmogar.njc.ast.expressions.Expression;
import com.davidmogar.njc.ast.expressions.operators.binary.ArithmeticOperator;
import com.davidmogar.njc.ast.expressions.operators.binary.ArrayAccessOperator;
import com.davidmogar.njc.ast.expressions.operators.binary.ComparisonOperator;
import com.davidmogar.njc.ast.expressions.operators.binary.LogicalOperator;
import com.davidmogar.njc.ast.expressions.operators.unary.CastOperator;
import com.davidmogar.njc.ast.expressions.operators.unary.NegationOperator;
import com.davidmogar.njc.ast.expressions.operators.unary.NotOperator;
import com.davidmogar.njc.ast.statements.AssignmentStatement;
import com.davidmogar.njc.ast.statements.InvocationStatement;
import com.davidmogar.njc.ast.statements.ReturnStatement;
import com.davidmogar.njc.ast.statements.controlflow.IfStatement;
import com.davidmogar.njc.ast.statements.controlflow.WhileStatement;
import com.davidmogar.njc.ast.statements.definitions.Definition;
import com.davidmogar.njc.ast.statements.definitions.FunctionDefinition;
import com.davidmogar.njc.ast.statements.definitions.VariableDefinition;
import com.davidmogar.njc.ast.statements.io.ReadStatement;
import com.davidmogar.njc.ast.types.FunctionType;
import com.davidmogar.njc.ast.types.Type;
import com.davidmogar.njc.visitors.AbstractVisitor;

public class SemanticVisitor extends AbstractVisitor {

    private boolean containsMainFunction;

    public SemanticVisitor() {
        containsMainFunction = false;
    }

    @Override
    public Object visit(Program program, Object object) {
        super.visit(program, object);

        if (!containsMainFunction) {
            new TypeError(program, "A valid program must contain a 'main' function");
        }

        return null;
    }

    @Override
    public Object visit(ArithmeticOperator arithmeticOperator, Object object) {
        super.visit(arithmeticOperator, object);

        Type leftExpressionType = arithmeticOperator.leftExpression.getType();
        Type rightExpressionType = arithmeticOperator.rightExpression.getType();

        Type type = leftExpressionType.inferArithmeticOperationType(rightExpressionType);
        if (type == null) {
            type = new TypeError(arithmeticOperator, "Incompatible types in arithmetic operation. Found '"
                    + leftExpressionType.getName() + "' and '" + rightExpressionType.getName() + "'");
        }
        arithmeticOperator.setType(type);

        return null;
    }

    @Override
    public Object visit(ArrayAccessOperator arrayAccessOperator, Object object) {
        super.visit(arrayAccessOperator, object);

        Type leftExpressionType = arrayAccessOperator.leftExpression.getType();
        Type rightExpressionType = arrayAccessOperator.rightExpression.getType();

        Type type = leftExpressionType.inferArrayAccessType(rightExpressionType);
        if (type == null) {
            type = new TypeError(arrayAccessOperator, "Incompatible types. Required 'int', found '" +
                    rightExpressionType.getName() + "'");
        }
        arrayAccessOperator.setType(type);

        return null;
    }

    @Override
    public Object visit(ComparisonOperator comparisonOperator, Object object) {
        super.visit(comparisonOperator, object);

        Type leftExpressionType = comparisonOperator.leftExpression.getType();
        Type rightExpressionType = comparisonOperator.rightExpression.getType();

        Type type = leftExpressionType.inferComparisonOperationType(rightExpressionType);
        if (type == null) {
            type = new TypeError(comparisonOperator, "Incompatible types in comparison operation. Found "
                    + leftExpressionType.getName() + " and " + rightExpressionType.getName());
        }
        comparisonOperator.setType(type);

        return null;
    }

    @Override
    public Object visit(LogicalOperator logicalOperator, Object object) {
        super.visit(logicalOperator, object);

        Type leftExpressionType = logicalOperator.leftExpression.getType();
        Type rightExpressionType = logicalOperator.rightExpression.getType();

        Type type = leftExpressionType.inferLogicOperationType(rightExpressionType);
        if (type == null) {
            type = new TypeError(logicalOperator, "Incompatible types in logical operation. Found "
                    + leftExpressionType.getName() + " and " + rightExpressionType.getName());
        }
        logicalOperator.setType(type);

        return null;
    }

    @Override
    public Object visit(CastOperator castOperator, Object object) {
        super.visit(castOperator, object);

        Type castType = castOperator.type;
        Type expressionType = castOperator.expression.getType();

        Type type = castType.inferCastType(expressionType);
        if (type == null) {
            type = new TypeError(castOperator, "Incompatible types. Cannot cast '"
                    + expressionType.getName() + "' to '" + castType.getName() + "'");
        }
        castOperator.setType(type);

        return null;
    }

    @Override
    public Object visit(NegationOperator negationOperator, Object object) {
        super.visit(negationOperator, object);

        Type type = negationOperator.expression.getType().inferArithmeticOperationType();
        if (type == null) {
            type = new TypeError(negationOperator, "Expression cannot be negated");
        }
        negationOperator.setType(type);

        return null;
    }

    @Override
    public Object visit(NotOperator notOperator, Object object) {
        super.visit(notOperator, object);

        Type type = notOperator.expression.getType().inferLogicOperationType();
        if (type == null) {
            type = new TypeError(notOperator, "Operator '!' cannot be applied to '" + notOperator.expression.getType().getName() + "'");
        }
        notOperator.setType(type);

        return null;
    }

    @Override
    public Object visit(IfStatement ifStatement, Object object) {
        super.visit(ifStatement, object);

        Type type = ifStatement.condition.getType();
        if (!type.isLogic()) {
            new TypeError(ifStatement, "Incompatible types. Required 'int', found '" + type.getName() + "'");
        }

        return null;
    }

    @Override
    public Object visit(WhileStatement whileStatement, Object object) {
        super.visit(whileStatement, object);

        Type type = whileStatement.condition.getType();
        if (!type.isLogic()) {
            new TypeError(whileStatement, "Incompatible types. Required 'int', found '" + type.getName() + "'");
        }

        return null;
    }

    @Override
    public Object visit(FunctionDefinition functionDefinition, Object object) {
        FunctionType functionType = (FunctionType) functionDefinition.getType();
        for (VariableDefinition variableDefinition : functionType.parameters) {
            Type variableType = variableDefinition.getType();
            if (variableType.isPrimitive()) {
                variableDefinition.accept(this, object);
            } else {
                new TypeError(functionDefinition, "Incompatible types on function parameters. Expected a primitive type" +
                        ", found '" + variableType.getName() + "'");
            }
        }

        if (!functionType.returnType.isPrimitive()) {
            new TypeError(functionDefinition, "Incompatible types on function return. Expected a primitive type, found '" +
                    functionType.returnType.getName() + "'");
        }

        /* Check if a valid main function exists */
        if (functionDefinition.getName().equals("main")) {
            containsMainFunction = true;
        }

        super.visit(functionDefinition, functionType.returnType);

        return null;
    }

    @Override
    public Object visit(AssignmentStatement assignmentStatement, Object object) {
        super.visit(assignmentStatement, object);

        if (assignmentStatement.leftExpression.isLeftValue()) {
            Type leftExpressionType = assignmentStatement.leftExpression.getType();
            Type rightExpressionType = assignmentStatement.rightExpression.getType();

            if (leftExpressionType.inferAssignmentType(rightExpressionType) == null) {
                new TypeError(assignmentStatement, "Incompatible types. Found '"
                        + rightExpressionType.getName() + "', expected '" + leftExpressionType.getName() + "'");
            }
        } else {
            new TypeError(assignmentStatement.leftExpression, "Variable expected");
        }

        return null;
    }

    @Override
    public Object visit(ReturnStatement returnStatement, Object object) {
        super.visit(returnStatement, object);

        Type type = (Type) object;
        Type returnType = returnStatement.expression.getType();
        if (!returnType.isPromotable(type)) {
            new TypeError(returnStatement, "Incompatible types on function return. Expected '" + type.getName() + "', found '" +
                    returnType.getName() + "'");
        }

        return null;
    }

    @Override
    public Object visit(ReadStatement readStatement, Object object) {
        super.visit(readStatement, object);

        readStatement.expressions.stream().filter(expression -> !expression.isLeftValue()).forEach(expression -> {
            new TypeError(expression, "Variable expected");
        });

        return null;
    }

    @Override
    public Object visit(InvocationStatement invocationStatement, Object object) {
        super.visit(invocationStatement, object);

        Type type = invocationStatement.variable.definition.getType().inferInvocationReturnType(invocationStatement.arguments);
        if (type == null) {
            type = new TypeError(invocationStatement, "Cannot resolve method '" + invocationStatement + "'");
        }
        invocationStatement.setType(type);

        return null;
    }

}
