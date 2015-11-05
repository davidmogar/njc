package com.davidmogar.njc.ast.types;

import com.davidmogar.njc.TypeError;
import com.davidmogar.njc.visitors.Visitor;
import com.davidmogar.njc.ast.AbstractAstNode;

public class DoubleType extends AbstractType implements Type {

    private static final int SIZE = 4;
    private static final String SUFFIX = "f";

    private static DoubleType instance;

    private DoubleType(int line, int column) {
        super(line, column);
    }

    public static DoubleType getInstance() { return getInstance(0, 0); }
    public static DoubleType getInstance(int line, int column) {
        if (instance == null) {
            instance = new DoubleType(line, column);
        }

        return instance;
    }

    @Override
    public String getName() {
        return "double";
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
        if (type instanceof DoubleType || type instanceof IntegerType
                || type instanceof CharacterType || type instanceof TypeError) {
            arithmeticType = type;
        }
        return arithmeticType;
    }

    @Override
    public Type inferAssignmentType(Type type) {
        Type assignmentType = null;
        if (type instanceof CharacterType || type instanceof IntegerType
                || type instanceof DoubleType || type instanceof TypeError) {
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
        if (type instanceof IntegerType || type instanceof TypeError) {
            comparisonType = type;
        } else if (type instanceof CharacterType || type instanceof DoubleType) {
            comparisonType = IntegerType.getInstance(this.getLine(), this.getColumn());
        }
        return comparisonType;
    }

    @Override
    public boolean isPrimitive() {
        return true;
    }

    @Override
    public boolean isPromotable(Type type) {
        return type instanceof DoubleType;
    }

    @Override
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }

}
