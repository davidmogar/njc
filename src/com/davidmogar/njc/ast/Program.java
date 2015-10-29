package com.davidmogar.njc.ast;

import com.davidmogar.njc.ast.statements.definitions.Definition;

import java.util.List;

public class Program implements AstNode {

    public List<Definition> definitions;

    public Program(List<Definition> definitions) {
        this.definitions = definitions;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        definitions.forEach(builder::append);
        return builder.toString();
    }

}
