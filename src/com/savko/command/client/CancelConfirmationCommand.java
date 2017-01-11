package com.savko.command.client;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.constant.HttpHeader;
import com.savko.dao.BookingDao;
import com.savko.dao.DaoException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CancelConfirmationCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(CancelConfirmationCommand.class);

    @Override
    public Action execute(HttpServletRequest request) {
        String requestId = request.getParameter("requestId");
        BookingDao bookingDao = new BookingDao();
        try {
            bookingDao.cancelConfirmation(Integer.parseInt(requestId));
        } catch (DaoException e) {
            LOGGER.error("Unable to update table 'request'", e);
        }
        return new ForwardAction(HttpHeader.REFERER);
    }
}
