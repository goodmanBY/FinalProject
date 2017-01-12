package com.savko.command.admin;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.dao.BookingDao;
import com.savko.dao.DaoException;
import com.savko.dao.UserDao;
import com.savko.entity.BookingRequest;
import com.savko.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserProfileCommand implements Command {
    @Override
    public Action execute(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        UserDao userDao = new UserDao();
        BookingDao bookingDao = new BookingDao();
        try {
            User user = userDao.takeUser(Integer.parseInt(userId));
            List<BookingRequest> bookingRequests = bookingDao.takeBookingRequestsByUserId(Integer.parseInt(userId));
            request.setAttribute("user", user);
            request.setAttribute("bookingRequests", bookingRequests);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return new ForwardAction("/admin/userProfile.jsp");
    }
}
