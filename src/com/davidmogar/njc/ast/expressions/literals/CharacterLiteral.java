package com.davidmogar.njc.ast.expressions.literals;

import com.davidmogar.njc.ast.expressions.AbstractExpression;
import com.davidmogar.njc.ast.types.CharacterType;
import com.davidmogar.njc.visitors.Visitor;

public class CharacterLiteral extends AbstractExpression {

    public char value;

    public CharacterLiteral(int line, int column, char value) {
        super(line, column, CharacterType.getInstance(line, column));
        this.value = value;
    }

    @Override
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }

}
