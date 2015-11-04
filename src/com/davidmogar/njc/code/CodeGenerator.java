package com.davidmogar.njc.code;

import com.davidmogar.njc.ast.types.Type;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CodeGenerator {

    private PrintWriter printWriter;

    public CodeGenerator(String input, String output) throws FileNotFoundException {
        printWriter = new PrintWriter(output);

        source(input);
        breakline();
    }

    public void add(Type type) {
        printWriter.println("\tadd" + type.getSuffix());
        printWriter.flush();
    }

    public void addi(Type type) {
        printWriter.println("\taddi");
        printWriter.flush();
    }

    public void and() {
        printWriter.println("\tand");
        printWriter.flush();
    }

    public void breakline() {
        printWriter.println();
        printWriter.flush();
    }

    public void call(String name) {
        printWriter.println("\tcall" + name);
        printWriter.flush();
    }

    public void cast(Type original, Type desired) {
        char desiredSuffix = desired.getSuffix().charAt(0);

        switch (original.getSuffix().charAt(0)) {
            case 'b':
                printWriter.println("\tb2i");
                if (desiredSuffix == 'f') {
                    printWriter.println("\tb2f");
                }
                break;
            case 'i':
                if (desiredSuffix == 'b') {
                    printWriter.println("\ti2b");
                } else if (desiredSuffix == 'f') {
                    printWriter.println("\ti2f");
                }
                break;
            case 'f':
                printWriter.println("\tf2i");
                if (desiredSuffix == 'b') {
                    printWriter.println("\ti2b");
                }
                break;
        }
    }

    public void comment(String comment) {
        printWriter.println("\t'\t" + comment);
        printWriter.flush();
    }

    public void comparison(String operator, Type type) {
        switch (operator) {
            case "==":
                eq(type);
                break;
            case ">=":
                ge(type);
                break;
            case ">":
                gt(type);
                break;
            case "<=":
                le(type);
                break;
            case "<":
                lt(type);
                break;
            case "!=":
                ne(type);
                break;
        }
    }

    public void div(Type type) {
        printWriter.println("\tdiv" + type.getSuffix());
        printWriter.flush();
    }

    public void divi(Type type) {
        printWriter.println("\tdivi");
        printWriter.flush();
    }

    public void enter(int offset) {
        printWriter.println("\tenter" + offset);
        printWriter.flush();
    }

    public void entryPoint() {
        printWriter.println("call main");
        printWriter.println("halt");
        printWriter.flush();
    }

    public void eq(Type type) {
        printWriter.println("\teq" + type.getSuffix());
        printWriter.flush();
    }

    public void ge(Type type) {
        printWriter.println("\tge" + type.getSuffix());
        printWriter.flush();
    }

    public void gt(Type type) {
        printWriter.println("\tgt" + type.getSuffix());
        printWriter.flush();
    }

    public void in(Type type) {
        printWriter.println("\tin" + type.getSuffix());
        printWriter.flush();
    }

    public void jmp(String tag) {
        printWriter.println("\tjmp " + tag);
        printWriter.flush();
    }

    public void jnz(String tag) {
        printWriter.println("\tjnz " + tag);
        printWriter.flush();
    }

    public void jz(String tag) {
        printWriter.println("\tjz " + tag);
        printWriter.flush();
    }

    public void le(Type type) {
        printWriter.println("\tle" + type.getSuffix());
        printWriter.flush();
    }

    public void line(int line) {
        printWriter.println("#line " + line);
        printWriter.flush();
    }

    public void load(Type type) {
        printWriter.println("\tload" + type.getSuffix());
        printWriter.flush();
    }

    public void logic(String operator) {
        switch (operator) {
            case "&&":
                and();
                break;
            case "||":
                or();
                break;
        }
    }

    public void lt(Type type) {
        printWriter.println("\tlt" + type.getSuffix());
        printWriter.flush();
    }

    public void modi() {
        printWriter.println("\tmodi");
        printWriter.flush();
    }

    public void mul(Type type) {
        printWriter.println("\tmul" + type.getSuffix());
        printWriter.flush();
    }

    public void muli(Type type) {
        printWriter.println("\tmuli");
        printWriter.flush();
    }

    public void ne(Type type) {
        printWriter.println("\tne" + type.getSuffix());
        printWriter.flush();
    }

    public void not() {
        printWriter.println("\tnot");
        printWriter.flush();
    }

    public void or() {
        printWriter.println("\tor");
        printWriter.flush();
    }

    public void out(Type type) {
        printWriter.println("\tout" + type.getSuffix());
        printWriter.flush();
    }

    public void push(char c) {
        printWriter.println("\tpushb " + (int) c);
        printWriter.flush();
    }

    public void push(double d) {
        printWriter.println("\tpushf " + d);
        printWriter.flush();
    }

    public void push(int i) {
        printWriter.println("\tpushi " + i);
        printWriter.flush();
    }

    public void pusha(int address) {
        printWriter.println("\tpusha " + address);
        printWriter.flush();
    }

    public void pushbp() {
        printWriter.println("\tpush bp");
        printWriter.flush();
    }

    public void ret(int bytesReturned, int variablesOffset, int parametersOffset) {
        printWriter.println("\tret " + bytesReturned + ", " + variablesOffset + ", " + parametersOffset);
        printWriter.flush();
    }

    public void source(String file) {
        printWriter.println("#source \"" + file + "\"");
        printWriter.flush();
    }

    public void store(Type type) {
        printWriter.println("\tstore" + type.getSuffix());
        printWriter.flush();
    }

    public void sub(Type type) {
        printWriter.println("\tsub" + type.getSuffix());
        printWriter.flush();
    }

    public void subi(Type type) {
        printWriter.println("\tsubi");
        printWriter.flush();
    }

    public void tag(String tag) {
        printWriter.println("tag: " + tag);
        printWriter.flush();
    }

}
