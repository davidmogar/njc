package com.davidmogar.njc.ast.types;

import com.davidmogar.njc.visitors.Visitor;
import com.davidmogar.njc.ast.AbstractAstNode;

public class VoidType extends AbstractType implements Type {

    private static VoidType instance;

    private VoidType(int line, int column) {
        super(line, column);
    }

    public static VoidType getInstance(int line, int column) {
        if (instance == null) {
            instance = new VoidType(line, column);
        }

        return instance;
    }

    @Override
    public String getName() {
        return "void";
    }

    @Override
    public boolean isPrimitive() {
        return true;
    }

    @Override
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }
    
}
