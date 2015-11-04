package com.davidmogar.njc.ast.types;

import com.davidmogar.njc.TypeError;
import com.davidmogar.njc.visitors.Visitor;

public class ArrayType extends AbstractType implements Type {

    public Type type;

    public int size;

    public ArrayType(int line, int column, Type type, int size) {
        super(line, column);
    }

    public static ArrayType createArray(Type type, int size) {
        ArrayType arrayType = null;

        if (type instanceof ArrayType) {
            arrayType = (ArrayType) type;

            Type currentNode = arrayType.type;
            while (currentNode instanceof ArrayType) {
                currentNode = ((ArrayType) currentNode).type;
            }

            arrayType.type = new ArrayType(type.getLine(), type.getColumn(), currentNode, size);;
        } else {
            arrayType =  new ArrayType(type.getLine(), type.getColumn(), type, size);
            arrayType.type = type;
        }

        return arrayType;
    }

    @Override
    public Type inferArrayAccessType(Type type) {
        Type arrayAccessType = null;
        if (type instanceof TypeError) {
            arrayAccessType = type;
        } else if (type.isPromotable(IntegerType.getInstance(getLine(), getColumn()))) {
            arrayAccessType =  this.type;
        }
        return arrayAccessType;
    }

    @Override
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }

}
