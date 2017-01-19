package com.savko.service;

import com.savko.dao.BookingDao;
import com.savko.dao.DaoException;
import com.savko.entity.BookingRequest;

import java.util.List;

public class BookingService {

    public static BookingService getInstance() {
        return StaticHolder.INSTANCE;
    }

    public void bookRequest(BookingRequest userRequest) throws ServiceException {
        try {
            BookingDao.getInstance().bookRequest(userRequest);
        } catch (DaoException e) {
            throw new ServiceException("Unable to book request.", e);
        }
    }

    public BookingRequest takeBookingRequestByRequestId(int requestId) throws ServiceException {
        try {
            return BookingDao.getInstance().takeBookingRequestByRequestId(requestId);
        } catch (DaoException e) {
            throw new ServiceException("Unable to take booking request by request ID.", e);
        }
    }

    public List<BookingRequest> takeAllBookingRequests() throws ServiceException {
        try {
            return BookingDao.getInstance().takeAllBookingRequests();
        } catch (DaoException e) {
            throw new ServiceException("Unable to take all booking requests.", e);
        }
    }

    public List<BookingRequest> takeBookingRequestsByUserId(int userId) throws ServiceException {
        try {
            return BookingDao.getInstance().takeBookingRequestsByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("Unable to take booking requests by user ID.", e);
        }
    }

    public void confirmBookingRequest(int requestId, String adminLogin) throws ServiceException {
        try {
            BookingDao.getInstance().confirmBookingRequest(requestId, adminLogin);
        } catch (DaoException e) {
            throw new ServiceException("Unable to confirm booking request.", e);
        }
    }

    public void cancelConfirmation(int requestId) throws ServiceException {
        try {
            BookingDao.getInstance().cancelConfirmation(requestId);
        } catch (DaoException e) {
            throw new ServiceException("Unable to cancel confirmation.", e);
        }
    }

    public int takeRoomCost() throws ServiceException {
        try {
            return BookingDao.getInstance().takeRoomCost();
        } catch (DaoException e) {
            throw new ServiceException("Unable to take cost of room.", e);
        }
    }

    private static class StaticHolder {
        static final BookingService INSTANCE = new BookingService();
    }

}
