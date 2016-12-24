package com.savko.command;

import com.savko.action.Action;
import com.savko.action.RedirectAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LanguageChangeCommand implements Command {

    @Override
    public Action execute(HttpServletRequest request) {
        String language = request.getParameter("language");
        HttpSession session = request.getSession();
        session.setAttribute("language", language);

        return new RedirectAction("/index.jsp");
    }
}
