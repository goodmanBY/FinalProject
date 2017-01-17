package com.savko.command.client;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.constant.HttpHeader;
import com.savko.constant.Pages;
import com.savko.constant.Parameters;
import com.savko.dao.BookingDao;
import com.savko.dao.DaoException;
import com.savko.service.BookingService;
import com.savko.service.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CancelConfirmationCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(CancelConfirmationCommand.class);

    @Override
    public Action execute(HttpServletRequest request) {
        String requestId = request.getParameter(Parameters.REQUEST_ID);

        try {
            BookingService.getInstance().cancelConfirmation(Integer.parseInt(requestId));
        } catch (ServiceException e) {
            LOGGER.error("Unable to update table 'request'.", e);
        }
        return new ForwardAction(Pages.ADMIN_CONTROL_PANEL);
    }
}
