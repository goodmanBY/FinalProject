package com.savko.command.client;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.constant.Pages;
import com.savko.dao.BookingDao;
import com.savko.dao.DaoException;
import com.savko.entity.Admin;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ConfirmBookingRequestCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(ConfirmBookingRequestCommand.class);

    @Override
    public Action execute(HttpServletRequest request) {
        String requestId = request.getParameter("requestId");
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        String adminLogin = admin.getLogin();
        BookingDao bookingDao = new BookingDao();
        try {
            bookingDao.confirmBookingRequest(Integer.parseInt(requestId), adminLogin);
        } catch (DaoException e) {
            LOGGER.error("Unable to update table 'request'", e);
        }
        return new ForwardAction(Pages.ADMIN_CONTROL_PANEL);
    }
}
