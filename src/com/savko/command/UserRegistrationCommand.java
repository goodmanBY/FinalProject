package com.savko.command;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.dao.UserDao;

import javax.servlet.http.HttpServletRequest;

public class UserRegistrationCommand implements Command {

    @Override
    public Action execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();
        userDao.addUser(userDao.createUser(name, lastName, login, password));

        return new ForwardAction("/index.jsp");
    }

}
