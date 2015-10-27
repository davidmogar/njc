package com.davidmogar.njc;

public class TypeError {

    private String message;

    private int column;
    private int line;

    public TypeError(int line, int column, String message) {
        this.line = line;
        this.column = column;
        this.message = message;

        ErrorHandler.getInstance().addTypeError(this);
    }

    @Override
    public String toString() {
        return "(" + line + ", " + column + ") " + message;
    }
}
