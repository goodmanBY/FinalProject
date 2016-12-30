package com.savko.command;


import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.dao.UserDao;
import com.savko.util.Hex;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserLogInCommand implements Command {

    @Override
    public Action execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = Hex.md5Custom(request.getParameter("password"));
        HttpSession session = request.getSession();
        UserDao userDao = new UserDao();
        if (userDao.checkUser(login, password)) {
            session.setAttribute("login", login);
            return new ForwardAction("/index.jsp");
        } else {
            request.setAttribute("error", "Incorrect login or password");
            return new ForwardAction("/logIn.jsp");
        }
    }
}