package com.davidmogar.njc;

import com.davidmogar.njc.code.ExecVisitor;
import com.davidmogar.njc.lexicon.Lexicon;
import com.davidmogar.njc.semantic.LinkerVisitor;
import com.davidmogar.njc.semantic.SemanticVisitor;
import com.davidmogar.njc.syntactic.Parser;
import com.davidmogar.njc.code.OffsetVisitor;

import java.io.File;
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
        if (args.length < 2) {
            System.err.println("Input file and output file expected.");
            System.exit(0);
        }

        File inputFile = null;
        FileReader fileReader = null;
        try {
            inputFile = new File(args[0]);
            fileReader = new FileReader(inputFile);
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
            parser.ast.accept(new OffsetVisitor(), null);
            parser.ast.accept(new ExecVisitor(inputFile.getName(), inputFile.getName() + ".o"), null);
        }

        showErrors();
    }

}
