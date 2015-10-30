package com.davidmogar.njc.ast.expressions;

import com.davidmogar.njc.ast.AstNode;

public interface Expression extends AstNode {

    boolean isLeftValue();

    void setLeftValue(boolean leftValue);

}
