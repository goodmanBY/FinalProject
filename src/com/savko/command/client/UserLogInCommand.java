package com.savko.command.client;


import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.dao.UserDao;
import com.savko.entity.User;
import com.savko.util.HashUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserLogInCommand implements Command {

    @Override
    public Action execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = HashUtil.getMd5Hash(request.getParameter("password"));
        HttpSession session = request.getSession();
        UserDao userDao = new UserDao();
        if (userDao.checkUser(login, password)) {
            User currentUser = userDao.takeUser(login);
            session.setAttribute("user", currentUser);
            return new ForwardAction("/index.jsp");
        } else {
            request.setAttribute("error", "Incorrect login or password");
            return new ForwardAction("/userLogIn.jsp");
        }
    }
}