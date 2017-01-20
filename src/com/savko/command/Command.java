package com.savko.command;


import com.savko.action.Action;
import com.savko.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    /**
     * @param request HttpServletRequest instance
     * @return child instance of abstract class Action
     */
    Action execute(HttpServletRequest request) throws CommandException;

}