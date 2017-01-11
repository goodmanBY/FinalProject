package com.savko.command.client;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.constant.Pages;
import com.savko.dao.DaoException;
import com.savko.dao.UserDao;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class BlockUserCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(BlockUserCommand.class);

    @Override
    public Action execute(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        UserDao userDao = new UserDao();
        try {
            userDao.blockUser(Integer.parseInt(userId));
        } catch (DaoException e) {
            LOGGER.error("Unable to update table 'client'", e);
        }
        return new ForwardAction(Pages.ADMIN_ALL_USERS);
    }
}
