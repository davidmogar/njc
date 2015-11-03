package com.davidmogar.njc.ast.expressions.literals;

import com.davidmogar.njc.ast.expressions.AbstractExpression;
import com.davidmogar.njc.ast.types.StringType;
import com.davidmogar.njc.visitors.Visitor;

public class StringLiteral extends AbstractExpression {

    public String value;

    public StringLiteral(int line, int column, String value) {
        super(line, column, StringType.getInstance(line, column));
        this.value = value;
    }

    @Override
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }

}
