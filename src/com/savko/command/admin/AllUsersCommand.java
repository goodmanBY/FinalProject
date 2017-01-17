package com.savko.command.admin;

import com.savko.action.Action;
import com.savko.action.RedirectAction;
import com.savko.command.Command;
import com.savko.constant.Attributes;
import com.savko.constant.Pages;
import com.savko.dao.DaoException;
import com.savko.dao.UserDao;
import com.savko.entity.User;
import com.savko.service.ServiceException;
import com.savko.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AllUsersCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(AllUsersCommand.class);

    @Override
    public Action execute(HttpServletRequest request) {
        List<User> users = null;
        try {
            users = UserService.getInstance().takeAllUsers();
        } catch (ServiceException e) {
            LOGGER.error("Unable to take all users.", e);
        }
        HttpSession session = request.getSession();
        session.setAttribute(Attributes.USERS, users);

        return new RedirectAction(Pages.ADMIN_ALL_USERS);
    }
}
