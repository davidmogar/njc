package com.davidmogar.njc.visitors;

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

public interface Visitor {

    Object visit(Program program, Object object);

    /* Expressions */

    Object visit(Variable variable, Object object);

    /* Literals */

    Object visit(CharacterLiteral characterLiteral, Object object);

    Object visit(DoubleLiteral doubleLiteral, Object object);

    Object visit(IntegerLiteral integerLiteral, Object object);

    Object visit(StringLiteral stringLiteral, Object object);

    /* Operators */

    /* Binary */

    Object visit(ArithmeticOperator arithmeticOperator, Object object);

    Object visit(ArrayAccessOperator arrayAccessOperator, Object object);

    Object visit(ComparisonOperator comparisonOperator, Object object);

    Object visit(LogicalOperator logicalOperator, Object object);

    /* Unary */

    Object visit(CastOperator castOperator, Object object);

    Object visit(NegationOperator negationOperator, Object object);

    Object visit(NotOperator notOperator, Object object);

    /* Statements */

    Object visit(AssignmentStatement assignmentStatement, Object object);

    Object visit(Block block, Object object);

    Object visit(InvocationStatement invocationStatement, Object object);

    Object visit(ReturnStatement returnStatement, Object object);

    /* Control flow */

    Object visit(IfStatement ifStatement, Object object);

    Object visit(WhileStatement whileStatement, Object object);

    /* Definitions */

    Object visit(FunctionDefinition functionDefinition, Object object);

    Object visit(VariableDefinition variableDefinition, Object object);

    Object visit(VariableDefinitionsGroup variableDefinitionsGroup, Object object);

    /* IO */

    Object visit(ReadStatement readStatement, Object object);

    Object visit(WriteStatement writeStatement, Object object);

    /* Types */

    Object visit(ArrayType arrayType, Object object);

    Object visit(CharacterType characterType, Object object);

    Object visit(DoubleType doubleType, Object object);

    Object visit(FunctionType functionType, Object object);

    Object visit(IntegerType integerType, Object object);

    Object visit(StringType stringType, Object object);

    Object visit(VoidType voidType, Object object);

    Object visit(TypeError typeError, Object object);

}
