package com.savko.command.exception;


import javax.servlet.ServletException;

public class CommandException extends ServletException {

    public CommandException(String exception) {
        super(exception);
    }

    public CommandException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

}
