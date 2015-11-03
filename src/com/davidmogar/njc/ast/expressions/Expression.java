package com.davidmogar.njc.ast.expressions;

import com.davidmogar.njc.ast.AstNode;
import com.davidmogar.njc.ast.types.Type;

public interface Expression extends AstNode {

    Type getType();

    boolean isLeftValue();

    void setLeftValue(boolean leftValue);

    void setType(Type type);

}
