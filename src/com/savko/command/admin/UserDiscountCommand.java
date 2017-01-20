package com.savko.command.admin;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.command.exception.CommandException;
import com.savko.constant.Pages;
import com.savko.service.ServiceException;
import com.savko.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UserDiscountCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(UserDiscountCommand.class);

    @Override
    public Action execute(HttpServletRequest request) throws CommandException {
        String discountValue = request.getParameter("discountValue");
        String userId = request.getParameter("userId");
        try {
            UserService.getInstance().changeUserDiscountValue(Integer.parseInt(discountValue), Integer.parseInt(userId));
        } catch (ServiceException e) {
            LOGGER.error("Unable to change user discount value.", e);
            throw new CommandException("Unable to change user discount value.", e);
        }

        return new ForwardAction(Pages.ADMIN_CONTROL_PANEL);
    }

}
