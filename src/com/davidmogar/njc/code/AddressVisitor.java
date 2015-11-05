package com.davidmogar.njc.code;

import com.davidmogar.njc.ast.expressions.Variable;
import com.davidmogar.njc.visitors.AbstractVisitor;

public class AddressVisitor extends AbstractVisitor {

    private ValueVisitor valueVisitor;
    private CodeGenerator codeGenerator;

    public AddressVisitor(CodeGenerator codeGenerator) {
        this.codeGenerator = codeGenerator;
    }

    public void setValueVisitor(ValueVisitor valueVisitor) {
        this.valueVisitor = valueVisitor;
    }

    @Override
    public Object visit(Variable variable, Object object) {
        if (variable.definition.getScope() == 0) {
            codeGenerator.pusha(variable.definition.getOffset());
        } else {
            codeGenerator.pushbp();
            codeGenerator.push(variable.definition.getOffset());
            codeGenerator.addi();
        }
        return super.visit(variable, object);
    }
}
