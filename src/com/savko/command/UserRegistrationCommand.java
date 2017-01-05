package com.savko.command;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.dao.UserDao;
import com.savko.entity.User;
import com.savko.util.HashUtils;

import javax.servlet.http.HttpServletRequest;

public class UserRegistrationCommand implements Command {

    @Override
    public Action execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String login = request.getParameter("login");
        String password = HashUtils.getMd5Hash(request.getParameter("password"));

        UserDao userDao = new UserDao();
        if (userDao.checkUserLogin(login)) {
            request.setAttribute("error", "User with such login exists");
            return new ForwardAction("/registration.jsp");
        } else {
            User user = new User(name, lastName, login, password);
            userDao.addUser(user);
            request.setAttribute("registered", "You successfully registered");
            return new ForwardAction("/registration.jsp");
        }
    }
}
