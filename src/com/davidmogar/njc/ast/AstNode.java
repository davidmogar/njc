package com.davidmogar.njc.ast;

import com.davidmogar.njc.Visitor;

public interface AstNode {

    int getLine();

    int getColumn();

    void accept(Visitor visitor, Object object);

}
