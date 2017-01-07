package com.savko.command.client;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.dao.UserDao;
import com.savko.entity.User;
import com.savko.util.HashUtil;

import javax.servlet.http.HttpServletRequest;

public class UserRegistrationCommand implements Command {

    @Override
    public Action execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String login = request.getParameter("login");
        String password = HashUtil.getMd5Hash(request.getParameter("password"));

        UserDao userDao = new UserDao();
        if (userDao.checkUserLogin(login)) {
            request.setAttribute("error", "User with such login exists");
            return new ForwardAction("userRegistration.jsp");
        } else {
            User user = new User(name, lastName, login, password);
            userDao.addUser(user);
            request.setAttribute("registered", "You successfully registered");
            return new ForwardAction("userRegistration.jsp");
        }
    }
}