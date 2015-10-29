package com.davidmogar.njc.ast;

import com.davidmogar.njc.ast.statements.Block;

import java.util.List;

public class Program implements AstNode {

    public List<Block> blocks;

    public Program(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        blocks.forEach(builder::append);
        return builder.toString();
    }
}
