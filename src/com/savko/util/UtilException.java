package com.savko.util;

import javax.servlet.ServletException;

public class UtilException extends ServletException {

    public UtilException(String exception){
        super(exception);
    }

    public UtilException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

}
