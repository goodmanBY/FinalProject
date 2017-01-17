package com.savko.command.client;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
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

public class UserRegistrationCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(UserRegistrationCommand.class);

    @Override
    public Action execute(HttpServletRequest request) {
        String name = request.getParameter(Parameters.NAME);
        String lastName = request.getParameter(Parameters.LAST_NAME);
        String login = request.getParameter(Parameters.LOGIN);
        String password = HashUtil.getMd5Hash(request.getParameter(Parameters.PASSWORD));
        try {
            if (UserService.getInstance().checkUserLogin(login)) {
                request.setAttribute(Attributes.ERROR, "User with such login exists");
                return new ForwardAction(Pages.USER_REGISTRATION);
            } else {
                User user = new User()
                        .setName(name)
                        .setLastName(lastName)
                        .setLogin(login)
                        .setPassword(password);
                UserService.getInstance().addUser(user);
                request.setAttribute(Attributes.REGISTERED, "You successfully registered");
                return new ForwardAction(Pages.USER_REGISTRATION);
            }
        } catch (ServiceException e) {
            LOGGER.error("Unable to register user.", e);
        }
        return null;
    }
}
