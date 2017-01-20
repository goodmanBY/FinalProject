package com.savko.command.client;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.command.exception.CommandException;
import com.savko.constant.Attributes;
import com.savko.constant.Pages;
import com.savko.constant.Parameters;
import com.savko.util.CostUtil;
import com.savko.util.DateUtil;
import com.savko.util.UtilException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UserRequestCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(UserRequestCommand.class);

    @Override
    public Action execute(HttpServletRequest request) throws CommandException {
        String amountOfPlaces = request.getParameter(Parameters.AMOUNT_OF_PLACES);
        String stringDateFrom = request.getParameter(Parameters.DATE_FROM);
        String stringDateTo = request.getParameter(Parameters.DATE_TO);
        try {
            if (DateUtil.areDatesValid(stringDateFrom, stringDateTo)) {
                int amountOfDays = DateUtil.calculateAmountOfDays(stringDateFrom, stringDateTo);
                double cost = CostUtil.calculateCost(Integer.parseInt(amountOfPlaces), amountOfDays);
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
        }
        return new ForwardAction(Pages.USER_REQUEST_INFO);
    }
}
