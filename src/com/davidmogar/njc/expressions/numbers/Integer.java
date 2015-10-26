package com.davidmogar.njc.expressions.numbers;

import com.davidmogar.njc.AbstractAstNode;

import java.lang.*;

public class Integer extends AbstractAstNode implements Number {

    public int value;

    public Integer(int line, int column, int value) {
        super(line, column);
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
