package com.davidmogar.njc;

import com.davidmogar.njc.lexicon.Lexicon;
import com.davidmogar.njc.visitors.LinkerVisitor;
import com.davidmogar.njc.semantic.SemanticVisitor;
import com.davidmogar.njc.syntactic.Parser;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {

    private static void showErrors() {
        List<TypeError> errors = ErrorHandler.getInstance().getTypeErrors();
        if (errors.size() > 0) {
            errors.forEach(System.err::println);
            System.exit(-1);
        }
    }

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

        showErrors();

        if (parser.ast != null) {
            parser.ast.accept(new LinkerVisitor(), null);
            showErrors();
            parser.ast.accept(new SemanticVisitor(), null);
        }

        showErrors();
    }

}
