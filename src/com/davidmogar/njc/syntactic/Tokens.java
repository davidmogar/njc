package com.davidmogar.njc.syntactic;

import java.lang.reflect.Field;

public abstract class Tokens {

    /* Primitive types */
    public static final int CHARACTER = 257;
    public static final int DOUBLE = 258;
    public static final int INTEGER = 259;
    public static final int VOID = 260;

    /* Control flow */
    public static final int IF = 270;
    public static final int WHILE = 271;

    /* Other reserved words */
    public static final int MAIN = 290;
    public static final int RETURN = 291;

    public static String getTokenNameByValue(int value) {
        String token = "";

        try {
            for (Field field : Tokens.class.getFields()) {
                if ((int) field.get(new Integer(0)) == value) {
                    token = field.getName();
                }
            }
        } catch (IllegalAccessException e) {
            // Just ignore it
        }

        return token;
    }
}
