package com.davidmogar.njc.ast.statements.blocks;

import com.davidmogar.njc.ast.statements.Statement;

import java.util.List;

public class FunctionBlock extends Block {

    public String name;
    public String returnType;

    public FunctionBlock(int line, int column, String name, String returnType, List<Statement> statements) {
        super(line, column, statements);
        this.name = name;
        this.returnType = returnType;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(returnType).append(" ").append(name).append("() {");

        for (Statement statement : statements) {
            builder.append("\n\t").append(statement);
        }

        return builder.append("\n}").toString();
    }
}
