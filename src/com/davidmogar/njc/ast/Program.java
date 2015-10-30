package com.davidmogar.njc.ast;

import com.davidmogar.njc.visitors.Visitor;
import com.davidmogar.njc.ast.statements.definitions.Definition;

import java.util.List;

public class Program extends AbstractAstNode {

    public List<Definition> definitions;

    public Program(int line, int column, List<Definition> definitions) {
        super(line, column);
        this.definitions = definitions;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        definitions.forEach(builder::append);
        return builder.toString();
    }

    @Override
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }

}
