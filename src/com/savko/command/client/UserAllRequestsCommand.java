package com.savko.command.client;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.constant.Attributes;
import com.savko.constant.Pages;
import com.savko.dao.BookingDao;
import com.savko.dao.DaoException;
import com.savko.entity.BookingRequest;
import com.savko.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserAllRequestsCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(UserAllRequestsCommand.class);

    @Override
    public Action execute(HttpServletRequest request) {
        BookingDao bookingDao = new BookingDao();
        User user = (User) request.getSession().getAttribute(Attributes.USER);
        List<BookingRequest> requests = null;
        try {
            requests = bookingDao.takeRequestsByUserId(user.getId());
        } catch (DaoException e) {
            LOGGER.error("Unable to take all user's requests.", e);
        }
        request.setAttribute("requests", requests);
        return new ForwardAction(Pages.USERS_REQUESTS);
    }
}
