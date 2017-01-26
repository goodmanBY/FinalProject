package com.savko.command.client;

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
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteBookingRequestCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(DeleteBookingRequestCommand.class);

    @Override
    public Action execute(HttpServletRequest request) throws CommandException {
        User user = (User) request.getSession().getAttribute(Attributes.USER);
        String requestId = request.getParameter(Parameters.REQUEST_ID);
        try {
            BookingService.getInstance().deleteBookingRequestByRequestId(Integer.parseInt(requestId));
            List<BookingRequest> requests = BookingService.getInstance().takeBookingRequestsByUserId(user.getId());
            request.setAttribute(Attributes.REQUESTS, requests);
        } catch (ServiceException e) {
            LOGGER.error("Unable to delete booking request by request ID.", e);
            throw new CommandException("Unable to delete booking request by request ID.", e);
        }

        return new ForwardAction(Pages.USERS_REQUESTS);
    }
}
