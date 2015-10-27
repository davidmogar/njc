package com.davidmogar.njc;

import com.davidmogar.njc.lexicon.Lexicon;
import com.davidmogar.njc.syntactic.Parser;
import com.davidmogar.njc.syntactic.Tokens;

import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Input file expected.");
            System.exit(0);
        }

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(args[0]);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }

        Lexicon lexicon = new Lexicon(fileReader);
        Parser parser = new Parser(lexicon);

        int token;
        while ((token = lexicon.yylex()) != 0) {
            System.out.println("(" + lexicon.getLine() + ", " + lexicon.getColumn() +
                    ") " + lexicon.matchedText + " -> " + Tokens.getTokenNameByValue(token));
        }

        ErrorHandler.getInstance().getTypeErrors().forEach(System.err::println);
    }

}
