package com.davidmogar.njc.code;

import com.davidmogar.njc.ast.expressions.Variable;
import com.davidmogar.njc.ast.expressions.literals.CharacterLiteral;
import com.davidmogar.njc.ast.expressions.literals.DoubleLiteral;
import com.davidmogar.njc.ast.expressions.literals.IntegerLiteral;
import com.davidmogar.njc.ast.expressions.operators.binary.ArithmeticOperator;
import com.davidmogar.njc.ast.types.Type;
import com.davidmogar.njc.visitors.AbstractVisitor;

public class ValueVisitor extends AbstractVisitor {

    private AddressVisitor addressVisitor;
    private CodeGenerator codeGenerator;

    public ValueVisitor(CodeGenerator codeGenerator) {
        this.codeGenerator = codeGenerator;
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
    public Object visit(Variable variable, Object object) {
        variable.accept(addressVisitor, object);
        codeGenerator.load(variable.getType());
        return null;
    }
}
