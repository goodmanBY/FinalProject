package com.savko.command.client;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.command.exception.CommandException;
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
import com.savko.validation.UserCardValidation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.security.Timestamp;
import java.util.Date;

public class PayRequestCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(PayRequestCommand.class);

    @Override
    public Action execute(HttpServletRequest request) throws CommandException {
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
        if (UserCardValidation.isCardValid(cardNumber, month, year, owner, securityCode)) {
            try {
                java.sql.Timestamp dateAndTime = DateUtil.getCurrentDateAndTime();
                paymentInfo.setUserId(userId)
                        .setRequestId(Integer.parseInt(requestId))
                        .setLastFourDigits(CardUtil.getFourLastDigits(cardNumber))
                        .setCost(Double.parseDouble(cost))
                        .setDateAndTime(dateAndTime);
                PaymentService.getInstance().payBookingRequestByRequestId(Integer.parseInt(requestId), paymentInfo);
                request.setAttribute(Attributes.PAID, "Wait for confirmation");
                return new ForwardAction(Pages.SUCCESS_PAID);
            } catch (ServiceException e) {
                LOGGER.error("Unable to pay request.", e);
                throw new CommandException("Unable to pay request.", e);
            } catch (UtilException e) {
                LOGGER.error("Unable to get current date and time.", e);
                throw new CommandException("Unable to get current date and time.", e);
            }
        } else {
            request.setAttribute(Attributes.ERROR, "Invalid card");
            return new ForwardAction(Pages.PAY_REQUEST);
        }
    }
}
