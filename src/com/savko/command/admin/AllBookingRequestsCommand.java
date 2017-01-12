package com.savko.command.admin;

import com.savko.action.Action;
import com.savko.action.RedirectAction;
import com.savko.command.Command;
import com.savko.constant.Attributes;
import com.savko.constant.Pages;
import com.savko.dao.BookingDao;
import com.savko.dao.DaoException;
import com.savko.entity.BookingRequest;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AllBookingRequestsCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(AllBookingRequestsCommand.class);

    @Override
    public Action execute(HttpServletRequest request) {
        BookingDao bookingDao = new BookingDao();
        List<BookingRequest> bookingRequests = null;
        try {
            bookingRequests = bookingDao.takeAllBookingRequests();
        } catch (DaoException e) {
            LOGGER.error("Unable to take all users.", e);
        }
        HttpSession session = request.getSession();
        session.setAttribute(Attributes.BOOKING_REQUESTS, bookingRequests);

        return new RedirectAction(Pages.ADMIN_ALL_BOOKING_REQUESTS);
    }
}