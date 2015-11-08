package com.davidmogar.njc.ast.types;

import com.davidmogar.njc.ast.AstNode;
import com.davidmogar.njc.ast.expressions.Expression;

import java.util.List;

public interface Type extends AstNode {

    Type biggest(Type type);

    String getName();

    int getSize();

    String getSuffix();

    Type inferArithmeticOperationType();

    Type inferArithmeticOperationType(Type type);

    Type inferArrayAccessType(Type type);

    Type inferAssignmentType(Type type);

    Type inferCastType(Type type);

    Type inferComparisonOperationType(Type type);

    Type inferLogicOperationType();

    Type inferLogicOperationType(Type type);

    Type inferInvocationReturnType(List<Expression> expressions);

    boolean isLogic();

    boolean isPrimitive();

    boolean isPromotable(Type type);

}
