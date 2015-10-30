package com.davidmogar.njc.visitors;

import com.davidmogar.njc.ast.Program;
import com.davidmogar.njc.ast.expressions.Expression;
import com.davidmogar.njc.ast.expressions.Variable;
import com.davidmogar.njc.ast.expressions.literals.CharacterLiteral;
import com.davidmogar.njc.ast.expressions.literals.DoubleLiteral;
import com.davidmogar.njc.ast.expressions.literals.IntegerLiteral;
import com.davidmogar.njc.ast.expressions.literals.StringLiteral;
import com.davidmogar.njc.ast.expressions.operators.binary.ArithmeticOperator;
import com.davidmogar.njc.ast.expressions.operators.binary.ArrayAccessOperator;
import com.davidmogar.njc.ast.expressions.operators.binary.ComparisonOperator;
import com.davidmogar.njc.ast.expressions.operators.binary.LogicalOperator;
import com.davidmogar.njc.ast.expressions.operators.unary.CastOperator;
import com.davidmogar.njc.ast.expressions.operators.unary.NegationOperator;
import com.davidmogar.njc.ast.expressions.operators.unary.NotOperator;
import com.davidmogar.njc.ast.expressions.operators.unary.UnaryOperator;
import com.davidmogar.njc.ast.statements.*;
import com.davidmogar.njc.ast.statements.controlflow.IfStatement;
import com.davidmogar.njc.ast.statements.controlflow.WhileStatement;
import com.davidmogar.njc.ast.statements.definitions.Definition;
import com.davidmogar.njc.ast.statements.definitions.FunctionDefinition;
import com.davidmogar.njc.ast.statements.definitions.VariableDefinition;
import com.davidmogar.njc.ast.statements.io.ReadStatement;
import com.davidmogar.njc.ast.statements.io.WriteStatement;
import com.davidmogar.njc.ast.types.*;

public class AbstractVisitor implements Visitor {

    @Override
    public Object visit(Program program, Object object) {
        for (Definition definition : program.definitions) {
            definition.accept(this, object);
        }
        return null;
    }

    @Override
    public Object visit(Variable variable, Object object) {
        return null;
    }

    @Override
    public Object visit(CharacterLiteral characterLiteral, Object object) {
        return null;
    }

    @Override
    public Object visit(DoubleLiteral doubleLiteral, Object object) {
        return null;
    }

    @Override
    public Object visit(IntegerLiteral integerLiteral, Object object) {
        return null;
    }

    @Override
    public Object visit(StringLiteral stringLiteral, Object object) {
        return null;
    }

    @Override
    public Object visit(ArithmeticOperator arithmeticOperator, Object object) {
        arithmeticOperator.leftExpression.accept(this, object);
        arithmeticOperator.rightExpression.accept(this, object);
        return null;
    }

    @Override
    public Object visit(ArrayAccessOperator arrayAccessOperator, Object object) {
        arrayAccessOperator.leftExpression.accept(this, object);
        arrayAccessOperator.rightExpression.accept(this, object);
        return null;
    }

    @Override
    public Object visit(ComparisonOperator comparisonOperator, Object object) {
        comparisonOperator.leftExpression.accept(this, object);
        comparisonOperator.rightExpression.accept(this, object);
        return null;
    }

    @Override
    public Object visit(LogicalOperator logicalOperator, Object object) {
        logicalOperator.leftExpression.accept(this, object);
        logicalOperator.rightExpression.accept(this, object);
        return null;
    }

    @Override
    public Object visit(CastOperator castOperator, Object object) {
        castOperator.expression.accept(this, object);
        castOperator.type.accept(this, object);
        return null;
    }

    @Override
    public Object visit(NegationOperator negationOperator, Object object) {
        negationOperator.accept(this, object);
        return null;
    }

    @Override
    public Object visit(NotOperator notOperator, Object object) {
        notOperator.accept(this, object);
        return null;
    }

    @Override
    public Object visit(AssignmentStatement assignmentStatement, Object object) {
        assignmentStatement.leftValue.accept(this, object);
        assignmentStatement.rightValue.accept(this, object);
        return null;
    }

    @Override
    public Object visit(Block block, Object object) {
        for (Statement statement : block.statements) {
            statement.accept(this, object);
        }
        return null;
    }

    @Override
    public Object visit(InvocationStatement invocationStatement, Object object) {
        for (Expression expression : invocationStatement.arguments) {
            expression.accept(this, object);
        }
        invocationStatement.variable.accept(this, object);
        return null;
    }

    @Override
    public Object visit(ReturnStatement returnStatement, Object object) {
        returnStatement.expression.accept(this, object);
        return null;
    }

    @Override
    public Object visit(IfStatement ifStatement, Object object) {
        ifStatement.condition.accept(this, object);
        ifStatement.ifBlock.accept(this, object);
        ifStatement.elseBlock.accept(this, object);
        return null;
    }

    @Override
    public Object visit(WhileStatement whileStatement, Object object) {
        whileStatement.condition.accept(this, object);
        whileStatement.block.accept(this, object);
        return null;
    }

    @Override
    public Object visit(FunctionDefinition functionDefinition, Object object) {
        functionDefinition.block.accept(this, object);
        return null;
    }

    @Override
    public Object visit(VariableDefinition variableDefinition, Object object) {
        return null;
    }

    @Override
    public Object visit(ReadStatement readStatement, Object object) {
        for (Expression expression : readStatement.expressions) {
            expression.accept(this, object);
        }
        return null;
    }

    @Override
    public Object visit(WriteStatement writeStatement, Object object) {
        for (Expression expression : writeStatement.expressions) {
            expression.accept(this, object);
        }
        return null;
    }

    @Override
    public Object visit(ArrayType arrayType, Object object) {
        arrayType.type.accept(this, object);
        return null;
    }

    @Override
    public Object visit(CharacterType characterType, Object object) {
        return null;
    }

    @Override
    public Object visit(DoubleType doubleType, Object object) {
        return null;
    }

    @Override
    public Object visit(FunctionType functionType, Object object) {
        for (VariableDefinition variableDefinition : functionType.parameters) {
            variableDefinition.accept(this, object);
        }
        functionType.returnType.accept(this, object);
        return null;
    }

    @Override
    public Object visit(IntegerType integerType, Object object) {
        return null;
    }

    @Override
    public Object visit(StringType stringType, Object object) {
        return null;
    }

    @Override
    public Object visit(VoidType voidType, Object object) {
        return null;
    }

}
