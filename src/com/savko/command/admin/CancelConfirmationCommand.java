package com.savko.command.admin;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.command.exception.CommandException;
import com.savko.constant.Pages;
import com.savko.constant.Parameters;
import com.savko.service.BookingService;
import com.savko.service.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CancelConfirmationCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(CancelConfirmationCommand.class);

    @Override
    public Action execute(HttpServletRequest request) throws CommandException {
        String requestId = request.getParameter(Parameters.REQUEST_ID);
        try {
            BookingService.getInstance().cancelConfirmation(Integer.parseInt(requestId));
        } catch (ServiceException e) {
            LOGGER.error("Unable to cancel confirmation.", e);
            throw new CommandException("Unable to cancel confirmation.", e);
        }
        return new ForwardAction(Pages.ADMIN_CONTROL_PANEL);
    }
}
