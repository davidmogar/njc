package com.davidmogar.njc.code;

import com.davidmogar.njc.ast.expressions.Expression;
import com.davidmogar.njc.ast.expressions.Variable;
import com.davidmogar.njc.ast.expressions.literals.CharacterLiteral;
import com.davidmogar.njc.ast.expressions.literals.DoubleLiteral;
import com.davidmogar.njc.ast.expressions.literals.IntegerLiteral;
import com.davidmogar.njc.ast.expressions.operators.binary.ArithmeticOperator;
import com.davidmogar.njc.ast.expressions.operators.binary.ArrayAccessOperator;
import com.davidmogar.njc.ast.expressions.operators.binary.ComparisonOperator;
import com.davidmogar.njc.ast.expressions.operators.binary.LogicalOperator;
import com.davidmogar.njc.ast.expressions.operators.unary.CastOperator;
import com.davidmogar.njc.ast.expressions.operators.unary.NegationOperator;
import com.davidmogar.njc.ast.expressions.operators.unary.NotOperator;
import com.davidmogar.njc.ast.statements.AssignmentStatement;
import com.davidmogar.njc.ast.statements.InvocationStatement;
import com.davidmogar.njc.ast.types.FunctionType;
import com.davidmogar.njc.ast.types.Type;

public class ValueVisitor extends AbstractCodeVisitor {

    private AddressVisitor addressVisitor;
    private CodeGenerator codeGenerator;
    private ExecVisitor execVisitor;

    public ValueVisitor(CodeGenerator codeGenerator, ExecVisitor execVisitor) {
        this.codeGenerator = codeGenerator;
        this.execVisitor = execVisitor;
    }

    public void setAddressVisitor(AddressVisitor addressVisitor) {
        this.addressVisitor = addressVisitor;
    }

    @Override
    public Object visit(CharacterLiteral characterLiteral, Object object) {
        codeGenerator.push(characterLiteral.value);
        return null;
    }

    @Override
    public Object visit(DoubleLiteral doubleLiteral, Object object) {
        codeGenerator.push(doubleLiteral.value);
        return null;
    }

    @Override
    public Object visit(IntegerLiteral integerLiteral, Object object) {
        codeGenerator.push(integerLiteral.value);
        return null;
    }

    @Override
    public Object visit(ArithmeticOperator arithmeticOperator, Object object) {
        Type type = arithmeticOperator.getType();

        arithmeticOperator.leftExpression.accept(this, object);
        codeGenerator.cast(arithmeticOperator.leftExpression.getType(), type);
        arithmeticOperator.rightExpression.accept(this, object);
        codeGenerator.cast(arithmeticOperator.rightExpression.getType(), type);
        codeGenerator.arithmetic(arithmeticOperator.operator, type);

        return null;
    }

    @Override
    public Object visit(ArrayAccessOperator arrayAccessOperator, Object object) {
        arrayAccessOperator.accept(addressVisitor, object);
        codeGenerator.load(arrayAccessOperator.getType());

        return null;
    }

    @Override
    public Object visit(ComparisonOperator comparisonOperator, Object object) {
        Type leftExpressionType = comparisonOperator.leftExpression.getType();
        Type rightExpressionType = comparisonOperator.rightExpression.getType();
        Type biggestType = leftExpressionType.biggest(rightExpressionType);
        comparisonOperator.leftExpression.accept(this, object);
        codeGenerator.cast(leftExpressionType, biggestType);
        comparisonOperator.rightExpression.accept(this, object);
        codeGenerator.cast(rightExpressionType, biggestType);
        codeGenerator.comparison(comparisonOperator.operator, biggestType);

        return null;
    }

    @Override
    public Object visit(LogicalOperator logicalOperator, Object object) {
        Type type = logicalOperator.getType();
        logicalOperator.leftExpression.accept(this, object);
        codeGenerator.cast(logicalOperator.leftExpression.getType(), type);
        logicalOperator.rightExpression.accept(this, object);
        codeGenerator.cast(logicalOperator.rightExpression.getType(), type);
        codeGenerator.logic(logicalOperator.operator);

        return null;
    }

    @Override
    public Object visit(CastOperator castOperator, Object object) {
        castOperator.expression.accept(this, object);
        codeGenerator.cast(castOperator.expression.getType(), castOperator.getType());

        return null;
    }

    @Override
    public Object visit(NegationOperator negationOperator, Object object) {
        Type type = negationOperator.getType();
        codeGenerator.push(0);
        negationOperator.expression.accept(this, object);
        codeGenerator.cast(negationOperator.expression.getType(), type);
        codeGenerator.sub(type);

        return null;
    }

    @Override
    public Object visit(NotOperator notOperator, Object object) {
        notOperator.expression.accept(this, object);
        codeGenerator.not();

        return null;
    }

    @Override
    public Object visit(Variable variable, Object object) {
        variable.accept(addressVisitor, object);
        codeGenerator.load(variable.getType());
        return null;
    }

    @Override
    public Object visit(InvocationStatement invocationStatement, Object object) {
        FunctionType functionType = (FunctionType) invocationStatement.getType();

        for (int i = 0; i < invocationStatement.arguments.size(); i++) {
            Expression expression = invocationStatement.arguments.get(i);
            expression.accept(this, object);
            codeGenerator.cast(expression.getType(), functionType.parameters.get(i).getType());
        }

        codeGenerator.call(invocationStatement.variable.name);

        return null;
    }

    @Override
    public Object visit(AssignmentStatement assignmentStatement, Object object) {
        assignmentStatement.accept(execVisitor, object);
        assignmentStatement.leftExpression.accept(addressVisitor, object);
        codeGenerator.load(assignmentStatement.leftExpression.getType());

        return null;
    }

}
