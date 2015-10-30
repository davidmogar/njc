package com.davidmogar.njc.visitors;

import com.davidmogar.njc.ast.Program;
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
import com.davidmogar.njc.ast.statements.AssignmentStatement;
import com.davidmogar.njc.ast.statements.Block;
import com.davidmogar.njc.ast.statements.InvocationStatement;
import com.davidmogar.njc.ast.statements.ReturnStatement;
import com.davidmogar.njc.ast.statements.controlflow.IfStatement;
import com.davidmogar.njc.ast.statements.controlflow.WhileStatement;
import com.davidmogar.njc.ast.statements.definitions.FunctionDefinition;
import com.davidmogar.njc.ast.statements.definitions.VariableDefinition;
import com.davidmogar.njc.ast.statements.io.ReadStatement;
import com.davidmogar.njc.ast.statements.io.WriteStatement;
import com.davidmogar.njc.ast.types.*;

public class AbstractVisitor implements Visitor {

    @Override
    public Object visit(Program program, Object object) {
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
        return null;
    }

    @Override
    public Object visit(ArrayAccessOperator arrayAccessOperator, Object object) {
        return null;
    }

    @Override
    public Object visit(ComparisonOperator comparisonOperator, Object object) {
        return null;
    }

    @Override
    public Object visit(LogicalOperator logicalOperator, Object object) {
        return null;
    }

    @Override
    public Object visit(CastOperator castOperator, Object object) {
        return null;
    }

    @Override
    public Object visit(NegationOperator negationOperator, Object object) {
        return null;
    }

    @Override
    public Object visit(NotOperator notOperator, Object object) {
        return null;
    }

    @Override
    public Object visit(UnaryOperator unaryOperator, Object object) {
        return null;
    }

    @Override
    public Object visit(AssignmentStatement assignmentStatement, Object object) {
        return null;
    }

    @Override
    public Object visit(Block block, Object object) {
        return null;
    }

    @Override
    public Object visit(InvocationStatement invocationStatement, Object object) {
        return null;
    }

    @Override
    public Object visit(ReturnStatement returnStatement, Object object) {
        return null;
    }

    @Override
    public Object visit(IfStatement ifStatement, Object object) {
        return null;
    }

    @Override
    public Object visit(WhileStatement whileStatement, Object object) {
        return null;
    }

    @Override
    public Object visit(FunctionDefinition functionDefinition, Object object) {
        return null;
    }

    @Override
    public Object visit(VariableDefinition variableDefinition, Object object) {
        return null;
    }

    @Override
    public Object visit(ReadStatement readStatement, Object object) {
        return null;
    }

    @Override
    public Object visit(WriteStatement writeStatement, Object object) {
        return null;
    }

    @Override
    public Object visit(ArrayType arrayType, Object object) {
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
