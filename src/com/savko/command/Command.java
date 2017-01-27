package com.savko.command;


import com.savko.action.Action;
import com.savko.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    Action execute(HttpServletRequest request) throws CommandException;

}