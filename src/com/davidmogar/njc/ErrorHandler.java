package com.davidmogar.njc;

import java.util.ArrayList;
import java.util.List;

public class ErrorHandler {

    private static ErrorHandler instance;

    private List<TypeError> typeErrors;

    private ErrorHandler() {
        typeErrors = new ArrayList<>();
    }

    public static synchronized ErrorHandler getInstance() {
        if (instance == null) {
            instance = new ErrorHandler();
        }

        return instance;
    }

    public void addTypeError(TypeError typeError) {
        typeErrors.add(typeError);
    }

    public List<TypeError> getTypeErrors() {
        return typeErrors;
    }

}
