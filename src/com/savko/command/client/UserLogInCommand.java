package com.savko.command.client;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.action.RedirectAction;
import com.savko.command.Command;
import com.savko.constant.Attributes;
import com.savko.constant.Pages;
import com.savko.constant.Parameters;
import com.savko.dao.DaoException;
import com.savko.dao.UserDao;
import com.savko.entity.User;
import com.savko.service.ServiceException;
import com.savko.service.UserService;
import com.savko.util.HashUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserLogInCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(UserLogInCommand.class);

    @Override
    public Action execute(HttpServletRequest request) {
        String login = request.getParameter(Parameters.LOGIN);
        String password = HashUtil.getMd5Hash(request.getParameter(Parameters.PASSWORD));
        HttpSession session = request.getSession();
        try {
            if (UserService.getInstance().checkUser(login, password)) {
                User currentUser = UserService.getInstance().takeUser(login);
                session.setAttribute(Attributes.USER, currentUser);
                return new RedirectAction(Pages.USER_INDEX);
            } else {
                request.setAttribute(Attributes.ERROR, "Incorrect login or password");
                return new ForwardAction(Pages.USER_LOG_IN);
            }
        } catch (ServiceException e) {
            LOGGER.error("Unable to log in user.", e);
        }
        return null;
    }
}