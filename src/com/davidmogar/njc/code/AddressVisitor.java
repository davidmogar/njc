package com.davidmogar.njc.code;

import com.davidmogar.njc.ast.expressions.Variable;
import com.davidmogar.njc.ast.expressions.operators.binary.ArrayAccessOperator;
import com.davidmogar.njc.ast.types.ArrayType;
import com.davidmogar.njc.ast.types.IntegerType;
import com.davidmogar.njc.ast.types.Type;

public class AddressVisitor extends AbstractCodeVisitor {

    private ValueVisitor valueVisitor;
    private CodeGenerator codeGenerator;

    public AddressVisitor(CodeGenerator codeGenerator) {
        this.codeGenerator = codeGenerator;
    }

    public void setValueVisitor(ValueVisitor valueVisitor) {
        this.valueVisitor = valueVisitor;
    }

    @Override
    public Object visit(ArrayAccessOperator arrayAccessOperator, Object object) {
        Type integerType = IntegerType.getInstance();
        arrayAccessOperator.leftExpression.accept(this, object);
        arrayAccessOperator.rightExpression.accept(valueVisitor, object);
        codeGenerator.cast(arrayAccessOperator.rightExpression.getType(), integerType);
        codeGenerator.push(((ArrayType) arrayAccessOperator.leftExpression.getType()).type.getSize());
        codeGenerator.mul(integerType);
        codeGenerator.add(integerType);

        return null;
    }

    @Override
    public Object visit(Variable variable, Object object) {
        if (variable.definition.getScope() == 0) {
            codeGenerator.pusha(variable.definition.getOffset());
        } else {
            codeGenerator.pushbp();
            codeGenerator.push(variable.definition.getOffset());
            codeGenerator.add(IntegerType.getInstance());
        }
        return null;
    }

}
