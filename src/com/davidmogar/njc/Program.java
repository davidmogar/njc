package com.davidmogar.njc;

import com.davidmogar.njc.statements.blocks.Block;

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
