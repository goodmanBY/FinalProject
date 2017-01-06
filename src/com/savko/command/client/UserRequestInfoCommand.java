package com.savko.command.client;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.dao.UserDao;
import com.savko.entity.Request;
import com.savko.util.CostUtil;
import com.savko.util.DateUtil;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

public class UserRequestInfoCommand implements Command {

    @Override
    public Action execute(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String amountOfPlaces = request.getParameter("amountOfPlaces");
        String stringDateFrom = request.getParameter("dateFrom");
        String stringDateTo = request.getParameter("dateTo");
        java.util.Date dateFrom = DateUtil.castToDate(stringDateFrom);
        java.util.Date dateTo = DateUtil.castToDate(stringDateTo);
        String cost = request.getParameter("cost");
        Request userRequest = new Request(Integer.parseInt(userId), Integer.parseInt(amountOfPlaces), dateFrom, dateTo, Double.parseDouble(cost));
        UserDao userDao = new UserDao();
        userDao.bookRequest(userRequest);
        return new ForwardAction("userProfile.jsp");
    }
}
