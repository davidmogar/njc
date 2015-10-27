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
            return;
        }

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(args[0]);
        } catch (IOException io) {
            System.err.println("El archivo "+args[0]+" no se ha podido abrir.");
            return;
        }

        Lexicon lexicon = new Lexicon(fileReader);
        Parser parser = new Parser(lexicon);

        int token;
        while ((token = lexicon.yylex()) != 0) {
            System.out.println("(" + lexicon.getLine() + ", " + lexicon.getColumn() +
                    ") " + lexicon.matchedText + " -> " + Tokens.getTokenNameByValue(token));
        }
    }
}

//void main() {
//    read a, b;
//    a = (-b + 3) * c / 2;
//    white a, c * 2;
//}
