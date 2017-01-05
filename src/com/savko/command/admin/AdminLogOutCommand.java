package com.savko.command.admin;


import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminLogOutCommand implements Command {

    public Action execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("adminLogin");
        return new ForwardAction("admin.jsp");
    }

}
