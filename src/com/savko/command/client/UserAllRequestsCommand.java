package com.savko.command.client;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.command.exception.CommandException;
import com.savko.constant.Attributes;
import com.savko.constant.Pages;
import com.savko.entity.BookingRequest;
import com.savko.entity.User;
import com.savko.service.BookingService;
import com.savko.service.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserAllRequestsCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(UserAllRequestsCommand.class);

    @Override
    public Action execute(HttpServletRequest request) throws CommandException {
        User user = (User) request.getSession().getAttribute(Attributes.USER);
        List<BookingRequest> requests;
        try {
            requests = BookingService.getInstance().takeBookingRequestsByUserId(user.getId());
        } catch (ServiceException e) {
            LOGGER.error("Unable to take all user's requests.", e);
            throw new CommandException("Unable to take all user's requests.", e);
        }
        request.setAttribute("requests", requests);
        return new ForwardAction(Pages.USERS_REQUESTS);
    }
}
