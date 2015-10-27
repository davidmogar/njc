package com.davidmogar.njc.syntactic;

import java.lang.reflect.Field;

public abstract class Tokens {

    /* Primitive types (from 260 to 279) */
    public static final int CHARACTER = 260;
    public static final int DOUBLE = 261;
    public static final int INTEGER = 262;

    /* Primitive types literals (from 279 to 299) */
    public static final int CHARACTER_LITERAL = 279;
    public static final int DOUBLE_LITERAL = 280;
    public static final int INTEGER_LITERAL = 281;

    /* Complex types (from 300 to 349) */
    public static final int STRING = 300;

    /* Complex types literals (from 350 to 399) */
    public static final int STRING_LITERAL = 350;

    /* Control flow (from 400 to 429) */
    public static final int IF = 400;
    public static final int WHILE = 401;

    /* Reserved words (from 430 to 729) */
    public static final int MAIN = 430;
    public static final int RETURN = 431;
    public static final int VOID = 432;

    /* Other tokens (from 730 onwards) */
    public static final int AND = 730;
    public static final int DECREMENT = 731;
    public static final int EQUALS = 732;
    public static final int GREATER_EQUALS = 733;
    public static final int IDENTIFIER = 734;
    public static final int INCREMENT = 735;
    public static final int LOWER_EQUALS = 736;
    public static final int NOT_EQUALS = 737;
    public static final int OR = 738;

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
