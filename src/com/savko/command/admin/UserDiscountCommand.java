package com.savko.command.admin;

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
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserDiscountCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(UserDiscountCommand.class);

    @Override
    public Action execute(HttpServletRequest request) throws CommandException {
        String discountValue = request.getParameter("discountValue");
        String userId = request.getParameter(Parameters.USER_ID);
        List<User> users;
        try {
            UserService.getInstance().changeUserDiscountValue(Integer.parseInt(discountValue), Integer.parseInt(userId));
            users = UserService.getInstance().takeAllUsers();
        } catch (ServiceException e) {
            LOGGER.error("Unable to change user discount value.", e);
            throw new CommandException("Unable to change user discount value.", e);
        }
        request.setAttribute(Attributes.USERS, users);

        return new ForwardAction(Pages.ADMIN_ALL_USERS);
    }

}
