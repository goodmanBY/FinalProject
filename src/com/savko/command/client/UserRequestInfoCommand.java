package com.savko.command.client;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.constant.Pages;
import com.savko.constant.Parameters;
import com.savko.dao.DaoException;
import com.savko.dao.UserDao;
import com.savko.entity.Request;
import com.savko.util.DateUtil;
import com.savko.util.UtilException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UserRequestInfoCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(UserRequestInfoCommand.class);

    @Override
    public Action execute(HttpServletRequest request) {
        String userId = request.getParameter(Parameters.USER_ID);
        String amountOfPlaces = request.getParameter(Parameters.AMOUNT_OF_PLACES);
        String stringDateFrom = request.getParameter(Parameters.DATE_FROM);
        String stringDateTo = request.getParameter(Parameters.DATE_TO);
        try {
            java.util.Date dateFrom = DateUtil.castToDate(stringDateFrom);
            java.util.Date dateTo = DateUtil.castToDate(stringDateTo);
            String cost = request.getParameter(Parameters.COST);
            Request userRequest = new Request(Integer.parseInt(userId), Integer.parseInt(amountOfPlaces), dateFrom, dateTo, Double.parseDouble(cost));
            UserDao userDao = new UserDao();
            userDao.bookRequest(userRequest);
        } catch (DaoException e) {
            LOGGER.error("Unable to book user's request.", e);
        } catch (UtilException e) {
            LOGGER.error("Unable to cast String to Date format" + e);
        }
        return new ForwardAction(Pages.USER_PROFILE);
    }
}
