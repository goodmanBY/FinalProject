package com.savko.command.admin;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.command.exception.CommandException;
import com.savko.constant.Pages;
import com.savko.entity.BookingRequest;
import com.savko.entity.User;
import com.savko.service.BookingService;
import com.savko.service.ServiceException;
import com.savko.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserProfileCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(UserProfileCommand.class);

    @Override
    public Action execute(HttpServletRequest request) throws CommandException {
        String userId = request.getParameter("userId");
        try {
            User user = UserService.getInstance().takeUser(Integer.parseInt(userId));
            List<BookingRequest> bookingRequests = BookingService.getInstance().takeBookingRequestsByUserId(Integer.parseInt(userId));
            request.setAttribute("user", user);
            request.setAttribute("bookingRequests", bookingRequests);
        } catch (ServiceException e) {
            LOGGER.error("Unable to take user or take requests from DB.", e);
            throw new CommandException("Unable to take user or take requests from DB.", e);
        }
        return new ForwardAction(Pages.ADMIN_USER_PROFILE);
    }
}
