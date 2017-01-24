package com.savko.command.client;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.command.exception.CommandException;
import com.savko.constant.Attributes;
import com.savko.constant.Pages;
import com.savko.constant.Parameters;
import com.savko.entity.User;
import com.savko.service.ServiceException;
import com.savko.service.UserService;
import com.savko.util.HashUtil;
import com.savko.validation.RegistrationValidation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UserRegistrationCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(UserRegistrationCommand.class);

    @Override
    public Action execute(HttpServletRequest request) throws CommandException {
        String name = request.getParameter(Parameters.NAME);
        String lastName = request.getParameter(Parameters.LAST_NAME);
        String login = request.getParameter(Parameters.LOGIN);
        String password = HashUtil.getMd5Hash(request.getParameter(Parameters.PASSWORD));
        String confirmPassword = HashUtil.getMd5Hash(request.getParameter(Parameters.CONFIRM_PASSWORD));
        try {
            if (RegistrationValidation.validateRegistrationForm(name, lastName, login, password, confirmPassword)) {
                if (!UserService.getInstance().checkUserLogin(login)) {
                    User user = new User()
                            .setName(name)
                            .setLastName(lastName)
                            .setLogin(login)
                            .setPassword(password);
                    UserService.getInstance().addUser(user);
                    request.setAttribute(Attributes.REGISTERED, "You successfully registered");
                    return new ForwardAction(Pages.USER_REGISTRATION);
                } else {
                    request.setAttribute(Attributes.ERROR, "User with such login exists");
                    return new ForwardAction(Pages.USER_REGISTRATION);
                }
            } else {
                request.setAttribute(Attributes.ERROR, "Invalid input data");
                return new ForwardAction(Pages.USER_REGISTRATION);
            }
        } catch (ServiceException e) {
            LOGGER.error("Unable to register user.", e);
            throw new CommandException("Unable to register user.", e);
        }
    }
}
