package com.savko.command;

import com.savko.action.Action;
import com.savko.action.ForwardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserLogOutCommand implements Command {

    @Override
    public Action execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return new ForwardAction("/index.jsp");
    }
}