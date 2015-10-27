package com.davidmogar.njc.syntactic;

import java.lang.reflect.Field;

public abstract class Tokens {

    /* Primitive types (from 260 to 269) */
    public static final int CHARACTER = 260;
    public static final int DOUBLE = 261;
    public static final int INTEGER = 262;

    /* Primitive types literals (from 270 to 279) */
    public static final int CHARACTER_LITERAL = 270;
    public static final int DOUBLE_LITERAL = 271;
    public static final int INTEGER_LITERAL = 272;

    /* Control flow (from 280 to 299) */
    public static final int IF = 280;
    public static final int WHILE = 281;

    /* Reserved words (from 300 to 399) */
    public static final int MAIN = 300;
    public static final int RETURN = 301;
    public static final int VOID = 302;

    /* Other tokens (from 400 onwards) */
    public static final int AND = 400;
    public static final int DECREMENT = 401;
    public static final int EQUALS = 402;
    public static final int GREATER_EQUALS = 403;
    public static final int IDENTIFIER = 404;
    public static final int INCREMENT = 405;
    public static final int LOWER_EQUALS = 406;
    public static final int NOT_EQUALS = 407;
    public static final int OR = 408;

    public static String getTokenNameByValue(int value) {
        String token = "ASCII";

        try {
            for (Field field : Tokens.class.getFields()) {
                if ((int) field.get(new Integer(0)) == value) {
                    token = field.getName();
                    break;
                }
            }
        } catch (IllegalAccessException e) {
            // Just ignore it
        }

        return token;
    }

}
