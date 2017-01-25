package com.savko.command.admin;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.action.RedirectAction;
import com.savko.command.Command;
import com.savko.command.exception.CommandException;
import com.savko.constant.Attributes;
import com.savko.constant.Pages;
import com.savko.entity.Discount;
import com.savko.entity.User;
import com.savko.service.ServiceException;
import com.savko.service.SettingService;
import com.savko.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AllUsersCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(AllUsersCommand.class);

    @Override
    public Action execute(HttpServletRequest request) throws CommandException {
        List<User> users;
        List<Discount> discounts;
        try {
            users = UserService.getInstance().takeAllUsers();
            discounts = SettingService.getInstance().takeAllDiscounts();
            request.setAttribute(Attributes.DISCOUNTS, discounts);
            request.setAttribute(Attributes.USERS, users);
        } catch (ServiceException e) {
            LOGGER.error("Unable to take all users.", e);
            throw new CommandException("Unable to take all users.", e);
        }

        return new ForwardAction(Pages.ADMIN_ALL_USERS);
    }
}
