package com.savko.command;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.dao.UserDao;
import com.savko.util.Hex;

import javax.servlet.http.HttpServletRequest;

public class UserRegistrationCommand implements Command {

    @Override
    public Action execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String login = request.getParameter("login");
        String password = Hex.md5Custom(request.getParameter("password"));

        UserDao userDao = new UserDao();
        if (userDao.checkUserLogin(login)) {
            request.setAttribute("error", "User with such login exists");
            return new ForwardAction("/registration.jsp");
        } else {
            userDao.addUser(userDao.createUser(name, lastName, login, password));
            request.setAttribute("registered", "You successfully registered");
            return new ForwardAction("/registration.jsp");
        }
    }

}
