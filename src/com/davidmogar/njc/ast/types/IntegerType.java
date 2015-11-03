package com.davidmogar.njc.ast.types;

import com.davidmogar.njc.TypeError;
import com.davidmogar.njc.visitors.Visitor;
import com.davidmogar.njc.ast.AbstractAstNode;

public class IntegerType extends AbstractType implements Type {

    private static IntegerType instance;

    private IntegerType(int line, int column) {
        super(line, column);
    }

    public static IntegerType getInstance(int line, int column) {
        if (instance == null) {
            instance = new IntegerType(line, column);
        }

        return instance;
    }

    @Override
    public String getName() {
        return "int";
    }

    @Override
    public Type inferArithmeticOperationType() {
        return this;
    }

    @Override
    public Type inferArithmeticOperationType(Type type) {
        Type arithmeticType = null;
        if (type instanceof DoubleType || type instanceof IntegerType || type instanceof TypeError) {
            arithmeticType = type;
        } else if (type instanceof CharacterType) {
            arithmeticType = this;
        }
        return arithmeticType;
    }

    @Override
    public Type inferAssignmentType(Type type) {
        Type assignmentType = null;
        if (type instanceof IntegerType || type instanceof DoubleType || type instanceof TypeError) {
            assignmentType = this;
        }
        return assignmentType;
    }

    @Override
    public Type inferCastType(Type type) {
        Type castType = null;
        if (type instanceof TypeError) {
            castType = type;
        } else if (type instanceof CharacterType || type instanceof IntegerType || type instanceof DoubleType) {
            castType = this;
        }
        return castType;
    }

    @Override
    public Type inferComparisonOperationType(Type type) {
        Type comparisonType = null;
        if (type instanceof TypeError) {
            comparisonType = type;
        } else if (type instanceof CharacterType || type instanceof IntegerType || type instanceof DoubleType) {
            comparisonType = this;
        }
        return comparisonType;
    }

    @Override
    public Type inferLogicOperationType() {
        return this;
    }

    @Override
    public Type inferLogicOperationType(Type type) {
        Type logicType = null;
        if (type instanceof TypeError) {
            logicType = type;
        } else if (type instanceof CharacterType || type instanceof IntegerType) {
            logicType = this;
        }
        return logicType;
    }

    @Override
    public boolean isPrimitive() {
        return true;
    }

    @Override
    public boolean isPromotable(Type type) {
        return type instanceof IntegerType || type instanceof DoubleType;
    }

    @Override
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }

}
