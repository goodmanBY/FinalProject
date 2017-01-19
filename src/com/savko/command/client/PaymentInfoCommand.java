package com.savko.command.client;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.constant.Attributes;
import com.savko.constant.Pages;
import com.savko.constant.Parameters;
import com.savko.entity.PaymentInfo;
import com.savko.service.PaymentService;
import com.savko.service.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class PaymentInfoCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(PaymentInfoCommand.class);

    @Override
    public Action execute(HttpServletRequest request) {
        String requestId = request.getParameter(Parameters.REQUEST_ID);
        try {
            PaymentInfo paymentInfo = PaymentService.getInstance().takePaymentInfoByRequestId(Integer.parseInt(requestId));
            request.setAttribute(Attributes.PAYMENT_INFO, paymentInfo);
        } catch (ServiceException e) {
            LOGGER.error("Unable to take data from DB.", e);
        }
        return new ForwardAction(Pages.PAYMENT_INFO);
    }

}