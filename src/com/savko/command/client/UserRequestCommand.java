package com.savko.command.client;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.command.exception.CommandException;
import com.savko.constant.Attributes;
import com.savko.constant.Pages;
import com.savko.constant.Parameters;
import com.savko.entity.User;
import com.savko.service.ServiceException;
import com.savko.service.UserService;
import com.savko.util.CostUtil;
import com.savko.util.DateUtil;
import com.savko.util.UtilException;
import com.savko.validation.DatesValidation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UserRequestCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(UserRequestCommand.class);

    @Override
    public Action execute(HttpServletRequest request) throws CommandException {
        User user = (User) request.getSession().getAttribute(Attributes.USER);
        String amountOfPlaces = request.getParameter(Parameters.AMOUNT_OF_PLACES);
        String stringDateFrom = request.getParameter(Parameters.DATE_FROM);
        String stringDateTo = request.getParameter(Parameters.DATE_TO);
        try {
            if (DatesValidation.areDatesValid(stringDateFrom, stringDateTo)) {
                int amountOfDays = DateUtil.calculateAmountOfDays(stringDateFrom, stringDateTo);
                int userDiscount = UserService.getInstance().takeDiscountValueByUserId(user.getId());
                double cost = CostUtil.calculateCost(Integer.parseInt(amountOfPlaces), amountOfDays, userDiscount);
                request.setAttribute(Parameters.AMOUNT_OF_PLACES, amountOfPlaces);
                request.setAttribute(Parameters.DATE_FROM, stringDateFrom);
                request.setAttribute(Parameters.DATE_TO, stringDateTo);
                request.setAttribute(Parameters.COST, cost);
            } else {
                request.setAttribute(Attributes.ERROR, "Bad selected dates");
                return new ForwardAction(Pages.USER_INDEX);
            }
        } catch (UtilException e) {
            LOGGER.error("Unable to book user's request.", e);
            throw new CommandException("Unable to book user's request.", e);
        } catch (ServiceException e) {
            LOGGER.error("Unable to take discount by user ID.", e);
            throw new CommandException("Unable to take discount by user ID.", e);
        }
        return new ForwardAction(Pages.USER_REQUEST_INFO);
    }
}
