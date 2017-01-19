package com.savko.command.client;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.constant.Attributes;
import com.savko.constant.Pages;
import com.savko.constant.Parameters;
import com.savko.entity.PaymentInfo;
import com.savko.entity.User;
import com.savko.service.PaymentService;
import com.savko.service.ServiceException;
import com.savko.util.CardUtil;
import com.savko.util.DateUtil;
import com.savko.util.UtilException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class PayRequestCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(PayRequestCommand.class);

    @Override
    public Action execute(HttpServletRequest request) {
        String requestId = request.getParameter(Parameters.REQUEST_ID);
        String cost = request.getParameter(Parameters.COST);
        String cardNumber = request.getParameter(Parameters.CARD_NUMBER);
        String month = request.getParameter(Parameters.MONTH);
        String year = request.getParameter(Parameters.YEAR);
        String owner = request.getParameter(Parameters.OWNER);
        String securityCode = request.getParameter(Parameters.SECURITY_CODE);
        PaymentInfo paymentInfo = new PaymentInfo();
        User currentUser = (User) request.getSession().getAttribute(Attributes.USER);
        int userId = currentUser.getId();
        if (CardUtil.isCardValid(cardNumber, Integer.parseInt(month), Integer.parseInt(year), owner, securityCode)) {
            try {
                Date dateAndTime = DateUtil.getCurrentDateAndTime();
                paymentInfo.setUserId(userId)
                        .setRequestId(Integer.parseInt(requestId))
                        .setLastFourDigits(CardUtil.getFourLastDigits(cardNumber))
                        .setCost(Double.parseDouble(cost))
                        .setDateAndTime(dateAndTime);
                PaymentService.getInstance().payBookingRequestByRequestId(Integer.parseInt(requestId));
                PaymentService.getInstance().addPaymentInfo(paymentInfo);
                request.setAttribute(Attributes.PAID, "Wait for confirmation");
                return new ForwardAction(Pages.USER_PROFILE);
            } catch (ServiceException e) {
                LOGGER.error("Unable to update table 'request'.", e);
            } catch (UtilException e) {
                LOGGER.error("Unable to get current date and time.", e);
            }
        } else {
            request.setAttribute(Attributes.ERROR, "Invalid card");
            return new ForwardAction(Pages.PAY_REQUEST);
        }
        return null;
    }
}
