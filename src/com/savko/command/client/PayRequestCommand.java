package com.savko.command.client;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.constant.Attributes;
import com.savko.constant.Pages;
import com.savko.constant.Parameters;
import com.savko.service.PaymentService;
import com.savko.service.ServiceException;
import com.savko.util.CardUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class PayRequestCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(PayRequestCommand.class);

    @Override
    public Action execute(HttpServletRequest request) {
        String requestId = request.getParameter(Parameters.REQUEST_ID);
        String cardNumber = request.getParameter(Parameters.CARD_NUMBER);
        String month = request.getParameter(Parameters.MONTH);
        String year = request.getParameter(Parameters.YEAR);
        String owner = request.getParameter(Parameters.OWNER);
        String securityCode = request.getParameter(Parameters.SECURITY_CODE);
        if (!CardUtil.isCardValid(cardNumber, Integer.parseInt(month), Integer.parseInt(year), owner, securityCode)) {
            request.setAttribute(Attributes.ERROR, "Invalid card");
            return new ForwardAction(Pages.PAY_REQUEST);
        } else {
            try {
                PaymentService.getInstance().payBookingRequestByRequestId(Integer.parseInt(requestId));
            } catch (ServiceException e) {
                LOGGER.error("Unable to update table 'request'.", e);
            }
            request.setAttribute(Attributes.PAID, "Wait for confirmation");
            return new ForwardAction(Pages.USER_PROFILE);
        }
    }
}
