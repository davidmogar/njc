package com.davidmogar.njc.ast.types;

import com.davidmogar.njc.TypeError;
import com.davidmogar.njc.visitors.Visitor;
import com.davidmogar.njc.ast.AbstractAstNode;

public class CharacterType extends AbstractType implements Type {

    private static final int SIZE = 1;
    private static final String SUFFIX = "b";

    private static CharacterType instance;

    private CharacterType(int line, int column) {
        super(line, column);
    }

    public static CharacterType getInstance(int line, int column) {
        if (instance == null) {
            instance = new CharacterType(line, column);
        }

        return instance;
    }

    @Override
    public String getName() {
        return "char";
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
        if (type instanceof IntegerType || type instanceof TypeError) {
            arithmeticType = type;
        } else if (type instanceof CharacterType) {
            arithmeticType = IntegerType.getInstance(this.getLine(), this.getColumn());
        }
        return arithmeticType;
    }

    @Override
    public Type inferAssignmentType(Type type) {
        Type assignmentType = null;
        if (type instanceof CharacterType || type instanceof TypeError) {
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
    public Type inferLogicOperationType() {
        return IntegerType.getInstance(this.getLine(), this.getColumn());
    }

    @Override
    public Type inferLogicOperationType(Type type) {
        Type logicType = null;
        if (type instanceof IntegerType || type instanceof TypeError) {
            logicType = type;
        } else if (type instanceof CharacterType) {
            logicType = IntegerType.getInstance(this.getLine(), this.getColumn());
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
        return type instanceof CharacterType || type instanceof IntegerType || type instanceof DoubleType;
    }

    @Override
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }

}
