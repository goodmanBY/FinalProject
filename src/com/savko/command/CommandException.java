package com.savko.command;


import javax.servlet.ServletException;

public class CommandException extends ServletException {

    CommandException(String exception){
        super(exception);
    }

}
