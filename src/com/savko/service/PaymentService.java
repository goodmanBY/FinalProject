package com.savko.service;

import com.savko.dao.DaoException;
import com.savko.dao.PaymentDao;

public class PaymentService {

    public static PaymentService getInstance() {
        return StaticHolder.INSTANCE;
    }

    private static class StaticHolder {
        static final PaymentService INSTANCE = new PaymentService();
    }

    public void payBookingRequestByRequestId(int requestId) throws ServiceException {
        try {
            PaymentDao.getInstance().payBookingRequestByRequestId(requestId);
        } catch (DaoException e) {
            throw new ServiceException("Unable to pay booking request by request ID.", e);
        }
    }

}
