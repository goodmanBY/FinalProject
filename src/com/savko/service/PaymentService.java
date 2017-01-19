package com.savko.service;

import com.savko.dao.DaoException;
import com.savko.dao.PaymentDao;
import com.savko.entity.PaymentInfo;

public class PaymentService {

    public static PaymentService getInstance() {
        return StaticHolder.INSTANCE;
    }

    public void payBookingRequestByRequestId(int requestId, PaymentInfo paymentInfo) throws ServiceException {
        try {
            PaymentDao.getInstance().payBookingRequestByRequestId(requestId);
            PaymentDao.getInstance().addPaymentInfo(paymentInfo);
        } catch (DaoException e) {
            throw new ServiceException("Unable to pay booking request by request ID.", e);
        }
    }

    public PaymentInfo takePaymentInfoByRequestId(int requestId) throws ServiceException {
        try {
            return PaymentDao.getInstance().takePaymentInfoByRequestId(requestId);
        } catch (DaoException e) {
            throw new ServiceException("Unable to add payment info.", e);
        }
    }

    private static class StaticHolder {
        static final PaymentService INSTANCE = new PaymentService();
    }

}
