package com.davidmogar.njc.code;

import com.davidmogar.njc.TypeError;
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
import com.davidmogar.njc.ast.statements.AssignmentStatement;
import com.davidmogar.njc.ast.statements.Block;
import com.davidmogar.njc.ast.statements.InvocationStatement;
import com.davidmogar.njc.ast.statements.ReturnStatement;
import com.davidmogar.njc.ast.statements.controlflow.IfStatement;
import com.davidmogar.njc.ast.statements.controlflow.WhileStatement;
import com.davidmogar.njc.ast.statements.definitions.FunctionDefinition;
import com.davidmogar.njc.ast.statements.definitions.VariableDefinition;
import com.davidmogar.njc.ast.statements.definitions.VariableDefinitionsGroup;
import com.davidmogar.njc.ast.statements.io.ReadStatement;
import com.davidmogar.njc.ast.statements.io.WriteStatement;
import com.davidmogar.njc.ast.types.*;
import com.davidmogar.njc.visitors.Visitor;

public abstract class AbstractCodeVisitor implements Visitor {

    @Override
    public Object visit(Program program, Object object) {
        throw new IllegalStateException("Program visitor not implemented");
    }

    @Override
    public Object visit(Variable variable, Object object) {
        throw new IllegalStateException("Variable visitor not implemented");
    }

    @Override
    public Object visit(CharacterLiteral characterLiteral, Object object) {
        throw new IllegalStateException("CharacterLiteral visitor not implemented");
    }

    @Override
    public Object visit(DoubleLiteral doubleLiteral, Object object) {
        throw new IllegalStateException("DoubleLiteral visitor not implemented");
    }

    @Override
    public Object visit(IntegerLiteral integerLiteral, Object object) {
        throw new IllegalStateException("IntegerLiteral visitor not implemented");
    }

    @Override
    public Object visit(StringLiteral stringLiteral, Object object) {
        throw new IllegalStateException("StringLiteral visitor not implemented");
    }

    @Override
    public Object visit(ArithmeticOperator arithmeticOperator, Object object) {
        throw new IllegalStateException("ArithmeticOperator visitor not implemented");
    }

    @Override
    public Object visit(ArrayAccessOperator arrayAccessOperator, Object object) {
        throw new IllegalStateException("ArrayAccessOperator visitor not implemented");
    }

    @Override
    public Object visit(ComparisonOperator comparisonOperator, Object object) {
        throw new IllegalStateException("ComparisonOperator visitor not implemented");
    }

    @Override
    public Object visit(LogicalOperator logicalOperator, Object object) {
        throw new IllegalStateException("LogicalOperator visitor not implemented");
    }

    @Override
    public Object visit(CastOperator castOperator, Object object) {
        throw new IllegalStateException("CastOperator visitor not implemented");
    }

    @Override
    public Object visit(NegationOperator negationOperator, Object object) {
        throw new IllegalStateException("NegationOperator visitor not implemented");
    }

    @Override
    public Object visit(NotOperator notOperator, Object object) {
        throw new IllegalStateException("NotOperator visitor not implemented");
    }

    @Override
    public Object visit(AssignmentStatement assignmentStatement, Object object) {
        throw new IllegalStateException("AssignmentStatement visitor not implemented");
    }

    @Override
    public Object visit(Block block, Object object) {
        throw new IllegalStateException("Block visitor not implemented");
    }

    @Override
    public Object visit(InvocationStatement invocationStatement, Object object) {
        throw new IllegalStateException("InvocationStatement visitor not implemented");
    }

    @Override
    public Object visit(ReturnStatement returnStatement, Object object) {
        throw new IllegalStateException("ReturnStatement visitor not implemented");
    }

    @Override
    public Object visit(IfStatement ifStatement, Object object) {
        throw new IllegalStateException("IfStatement visitor not implemented");
    }

    @Override
    public Object visit(WhileStatement whileStatement, Object object) {
        throw new IllegalStateException("WhileStatement visitor not implemented");
    }

    @Override
    public Object visit(FunctionDefinition functionDefinition, Object object) {
        throw new IllegalStateException("FunctionDefinition visitor not implemented");
    }

    @Override
    public Object visit(VariableDefinition variableDefinition, Object object) {
        throw new IllegalStateException("VariableDefinition visitor not implemented");
    }

    @Override
    public Object visit(VariableDefinitionsGroup variableDefinitionsGroup, Object object) {
        throw new IllegalStateException("VariableDefinitionsGroup visitor not implemented");
    }

    @Override
    public Object visit(ReadStatement readStatement, Object object) {
        throw new IllegalStateException("ReadStatement visitor not implemented");
    }

    @Override
    public Object visit(WriteStatement writeStatement, Object object) {
        throw new IllegalStateException("WriteStatement visitor not implemented");
    }

    @Override
    public Object visit(ArrayType arrayType, Object object) {
        throw new IllegalStateException("ArrayType visitor not implemented");
    }

    @Override
    public Object visit(CharacterType characterType, Object object) {
        throw new IllegalStateException("CharacterType visitor not implemented");
    }

    @Override
    public Object visit(DoubleType doubleType, Object object) {
        throw new IllegalStateException("DoubleType visitor not implemented");
    }

    @Override
    public Object visit(FunctionType functionType, Object object) {
        throw new IllegalStateException("FunctionType visitor not implemented");
    }

    @Override
    public Object visit(IntegerType integerType, Object object) {
        throw new IllegalStateException("IntegerType visitor not implemented");
    }

    @Override
    public Object visit(StringType stringType, Object object) {
        throw new IllegalStateException("StringType visitor not implemented");
    }

    @Override
    public Object visit(VoidType voidType, Object object) {
        throw new IllegalStateException("VoidType visitor not implemented");
    }

    @Override
    public Object visit(TypeError typeError, Object object) {
        throw new IllegalStateException("TypeError visitor not implemented");
    }
    
}
