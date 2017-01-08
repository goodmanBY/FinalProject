package com.savko.command.admin;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.constant.Attributes;
import com.savko.constant.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminLogOutCommand implements Command {

    public Action execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Attributes.ADMIN);
        return new ForwardAction(Pages.ADMIN_INDEX);
    }

}
