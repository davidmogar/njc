package com.davidmogar.njc.ast.types;

import com.davidmogar.njc.TypeError;
import com.davidmogar.njc.visitors.Visitor;
import com.davidmogar.njc.ast.AbstractAstNode;

public class IntegerType extends AbstractType implements Type {

    public static final int SIZE = 2;
    public static final String SUFFIX = "i";

    private static IntegerType instance;

    private IntegerType(int line, int column) {
        super(line, column);
    }

    public static IntegerType getInstance() { return getInstance(0, 0); }
    public static IntegerType getInstance(int line, int column) {
        if (instance == null) {
            instance = new IntegerType(line, column);
        }

        return instance;
    }

    @Override
    public Type biggest(Type type) {
        return (type instanceof DoubleType)? type : this;
    }

    @Override
    public String getName() {
        return "int";
    }

    @Override
    public int getSize() {
        return SIZE;
    }

    @Override
    public String getSuffix() {
        return SUFFIX;
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
    public boolean isLogic() {
        return true;
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
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }

}
