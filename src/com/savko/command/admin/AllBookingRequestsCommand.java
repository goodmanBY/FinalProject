package com.savko.command.admin;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.action.RedirectAction;
import com.savko.command.Command;
import com.savko.command.exception.CommandException;
import com.savko.constant.Attributes;
import com.savko.constant.Pages;
import com.savko.constant.Parameters;
import com.savko.entity.BookingRequest;
import com.savko.service.BookingService;
import com.savko.service.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AllBookingRequestsCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(AllBookingRequestsCommand.class);

    @Override
    public Action execute(HttpServletRequest request) throws CommandException {
        List<BookingRequest> bookingRequests;
        try {
            bookingRequests = BookingService.getInstance().takeAllBookingRequests();
        } catch (ServiceException e) {
            LOGGER.error("Unable to take all booking requests.", e);
            throw new CommandException("Unable to take all booking requests.", e);
        }
        request.setAttribute(Attributes.BOOKING_REQUESTS, bookingRequests);

        return new ForwardAction(Pages.ADMIN_ALL_BOOKING_REQUESTS);
    }
}
