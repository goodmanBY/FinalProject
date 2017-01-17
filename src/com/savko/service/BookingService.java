package com.savko.service;

import com.savko.dao.BookingDao;
import com.savko.dao.DaoException;
import com.savko.entity.BookingRequest;

import java.util.List;

public class BookingService {

    public static BookingService getInstance() {
        return StaticHolder.INSTANCE;
    }

    private static class StaticHolder {
        static final BookingService INSTANCE = new BookingService();
    }

    public void bookRequest(BookingRequest userRequest) throws ServiceException {
        try {
            BookingDao.getInstance().bookRequest(userRequest);
        } catch (DaoException e) {
            throw new ServiceException("Unable to book user's request.", e);
        }
    }

    public BookingRequest takeBookingRequestByRequestId(int requestId) throws ServiceException {
        try {
            return BookingDao.getInstance().takeBookingRequestByRequestId(requestId);
        } catch (DaoException e) {
            throw new ServiceException("Unable to take request from DB.", e);
        }
    }

    public List<BookingRequest> takeAllBookingRequests() throws ServiceException {
        try {
            return BookingDao.getInstance().takeAllBookingRequests();
        } catch (DaoException e) {
            throw new ServiceException("Unable to take all booking requests from 'request' table.", e);
        }
    }

    public List<BookingRequest> takeBookingRequestsByUserId(int userId) throws ServiceException {
        try {
            return BookingDao.getInstance().takeBookingRequestsByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("Unable to take requests from DB.", e);
        }
    }

    public void confirmBookingRequest(int requestId, String adminLogin) throws ServiceException {
        try {
            BookingDao.getInstance().confirmBookingRequest(requestId, adminLogin);
        } catch (DaoException e) {
            throw new ServiceException("Unable to update table 'request'.", e);
        }
    }

    public void cancelConfirmation(int requestId) throws ServiceException {
        try {
            BookingDao.getInstance().cancelConfirmation(requestId);
        } catch (DaoException e) {
            throw new ServiceException("Unable to update table 'request'.", e);
        }
    }

    public int takeRoomCost() throws ServiceException {
        try {
            return BookingDao.getInstance().takeRoomCost();
        } catch (DaoException e) {
            throw new ServiceException("Unable to take cost of room from 'setting' table.", e);
        }
    }

}
