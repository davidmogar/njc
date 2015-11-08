package com.davidmogar.njc.ast.types;

import com.davidmogar.njc.ast.AbstractAstNode;
import com.davidmogar.njc.ast.expressions.Expression;

import java.util.List;

public abstract class AbstractType extends AbstractAstNode implements Type {

    public AbstractType(int line, int column) {
        super(line, column);
    }

    @Override
    public Type biggest(Type type) {
        return type;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName().replace("Type", "");
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public String getSuffix() {
        return "";
    }

    @Override
    public Type inferArithmeticOperationType() {
        return null;
    }

    @Override
    public Type inferArithmeticOperationType(Type type) {
        return null;
    }

    @Override
    public Type inferArrayAccessType(Type type) {
        return null;
    }

    @Override
    public Type inferAssignmentType(Type type) {
        return null;
    }

    @Override
    public Type inferCastType(Type type) {
        return null;
    }

    @Override
    public Type inferComparisonOperationType(Type type) {
        return null;
    }

    @Override
    public Type inferLogicOperationType() {
        return null;
    }

    @Override
    public Type inferLogicOperationType(Type type) {
        return null;
    }

    @Override
    public Type inferInvocationReturnType(List<Expression> expressions) {
        return null;
    }

    @Override
    public boolean isLogic() {
        return false;
    }

    @Override
    public boolean isPrimitive() {
        return false;
    }

    @Override
    public boolean isPromotable(Type type) {
        return false;
    }

}
