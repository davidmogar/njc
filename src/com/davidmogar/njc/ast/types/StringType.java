package com.davidmogar.njc.ast.types;

import com.davidmogar.njc.TypeError;
import com.davidmogar.njc.visitors.Visitor;
import com.davidmogar.njc.ast.AbstractAstNode;

public class StringType extends AbstractType implements Type {

    private static StringType instance;

    private StringType(int line, int column) {
        super(line, column);
    }

    public static StringType getInstance(int line, int column) {
        if (instance == null) {
            instance = new StringType(line, column);
        }

        return instance;
    }

    @Override
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }

}
