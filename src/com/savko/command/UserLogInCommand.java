package com.savko.command;


import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserLogInCommand implements Command {

    @Override
    public Action execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        UserDao userDao = new UserDao();
        if (userDao.checkUser(login, password)) {
            request.setAttribute("message", "Welcome, " + login);
            session.setAttribute("login", login);
        } else {
            request.setAttribute("message", "Wrong login or password");
        }
        return new ForwardAction("/index.jsp");
    }
}