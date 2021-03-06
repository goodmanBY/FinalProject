package com.savko.command.admin;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.command.exception.CommandException;
import com.savko.constant.Attributes;
import com.savko.constant.Pages;
import com.savko.constant.Parameters;
import com.savko.entity.BookingRequest;
import com.savko.entity.User;
import com.savko.service.BookingService;
import com.savko.service.ServiceException;
import com.savko.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CancelConfirmationCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(CancelConfirmationCommand.class);

    @Override
    public Action execute(HttpServletRequest request) throws CommandException {
        String requestId = request.getParameter(Parameters.REQUEST_ID);
        List<BookingRequest> bookingRequests;
        User user;
        try {
            BookingService.getInstance().cancelConfirmation(Integer.parseInt(requestId));
            user = UserService.getInstance().takeUserByRequestId(Integer.parseInt(requestId));
            bookingRequests = BookingService.getInstance().takeBookingRequestsByUserId(user.getId());
            request.setAttribute(Attributes.USER, user);
            request.setAttribute(Attributes.BOOKING_REQUESTS, bookingRequests);
        } catch (ServiceException e) {
            LOGGER.error("Unable to cancel confirmation.", e);
            throw new CommandException("Unable to cancel confirmation.", e);
        }

        return new ForwardAction(Pages.ADMIN_USER_PROFILE);
    }
}
