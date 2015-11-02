package com.davidmogar.njc;

import com.davidmogar.njc.lexicon.Lexicon;
import com.davidmogar.njc.visitors.LinkerVisitor;
import com.davidmogar.njc.semantic.SemanticVisitor;
import com.davidmogar.njc.syntactic.Parser;

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
        parser.run();
        parser.ast.accept(new SemanticVisitor(), null);
        parser.ast.accept(new LinkerVisitor(), null);

        ErrorHandler.getInstance().getTypeErrors().forEach(System.err::println);
    }

}
