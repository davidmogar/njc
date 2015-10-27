package com.davidmogar.njc.syntactic;

import com.davidmogar.njc.lexicon.Lexicon;

public class Parser {

    private Lexicon lexicon;

    public Parser(Lexicon lexicon) {
        this.lexicon = lexicon;
    }

    public Object getMatchedText() {
        return lexicon.matchedText;
    }

}
