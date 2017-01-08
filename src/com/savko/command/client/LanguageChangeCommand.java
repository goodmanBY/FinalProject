package com.savko.command.client;

import com.savko.action.Action;
import com.savko.action.RedirectAction;
import com.savko.command.Command;
import com.savko.constant.HttpHeader;
import com.savko.constant.Parameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LanguageChangeCommand implements Command {

    @Override
    public Action execute(HttpServletRequest request) {
        String language = request.getParameter(Parameters.LANGUAGE);
        HttpSession session = request.getSession();
        session.setAttribute(Parameters.LANGUAGE, language);

        return new RedirectAction(request.getHeader(HttpHeader.REFERER));
    }
}
