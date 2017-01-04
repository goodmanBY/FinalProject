package com.savko.command;

import com.savko.action.Action;
import com.savko.action.RedirectAction;
import com.savko.dao.AdminDao;
import com.savko.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AllUsersCommand implements Command {
    @Override
    public Action execute(HttpServletRequest request) {
        AdminDao adminDao = new AdminDao();
        List<User> users = adminDao.takeAllUsers();
        HttpSession session = request.getSession();
        session.setAttribute("users", users);

        return new RedirectAction("/users.jsp");
    }
}
