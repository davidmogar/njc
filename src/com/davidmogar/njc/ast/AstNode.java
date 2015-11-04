package com.davidmogar.njc.ast;

import com.davidmogar.njc.visitors.Visitor;

public interface AstNode {

    int getLine();

    int getColumn();

    Object accept(Visitor visitor, Object object);

}
