package com.davidmogar.njc.ast.types;

import com.davidmogar.njc.visitors.Visitor;
import com.davidmogar.njc.ast.AbstractAstNode;

public class ArrayType extends AbstractAstNode implements Type {

    private Type type;

    private int size;

    public ArrayType(int line, int column, Type type, int size) {
        super(line, column);
    }

    public static ArrayType createArray(Type type, int size) {
        if (type instanceof ArrayType) {
            ArrayType arrayType = (ArrayType) type;

            Type currentNode = arrayType.type;
            while (currentNode instanceof ArrayType) {
                currentNode = ((ArrayType) currentNode).type;
            }

            arrayType.type = new ArrayType(type.getLine(), type.getColumn(), currentNode, size);;
            return arrayType;
        } else {
            return new ArrayType(type.getLine(), type.getColumn(), type, size);
        }
    }

    public Type getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }

}
