package com.davidmogar.njc.ast.types;

import com.davidmogar.njc.ast.AbstractAstNode;

public class CharacterType extends AbstractAstNode implements Type {

    private static CharacterType instance;

    private CharacterType(int line, int column) {
        super(line, column);
    }

    public CharacterType getInstance(int line, int column) {
        if (instance == null) {
            instance = new CharacterType(line, column);
        }

        return instance;
    }
    
}