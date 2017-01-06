package com.savko.command.admin;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.dao.AdminDao;
import com.savko.util.HashUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminLogInCommand implements Command {

    @Override
    public Action execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = HashUtil.getMd5Hash(request.getParameter("password"));
        HttpSession session = request.getSession();
        AdminDao adminDao = new AdminDao();
        if (adminDao.checkAdmin(login, password)) {
            session.setAttribute("adminLogin", login);
            return new ForwardAction("adminControlPanel.jsp");
        } else {
            request.setAttribute("error", "You are not admin");
            return new ForwardAction("adminLogIn.jsp");
        }
    }
}
