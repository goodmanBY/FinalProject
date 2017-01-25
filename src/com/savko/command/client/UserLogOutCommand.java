package com.savko.command.client;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.constant.Attributes;
import com.savko.constant.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserLogOutCommand implements Command {

    @Override
    public Action execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Attributes.USER);

        return new ForwardAction(Pages.USER_INDEX);
    }
}