package com.savko.command.client;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.util.CostUtil;
import com.savko.util.DateUtil;

import javax.servlet.http.HttpServletRequest;

public class UserRequestCommand implements Command {

    @Override
    public Action execute(HttpServletRequest request) {
        String amountOfPlaces = request.getParameter("amountOfPlaces");
        String stringDateFrom = request.getParameter("dateFrom");
        String stringDateTo = request.getParameter("dateTo");

        int amountOfDays = DateUtil.calculateAmountOfDays(stringDateFrom, stringDateTo);
        double cost = CostUtil.calculateCost(Integer.parseInt(amountOfPlaces), amountOfDays);
        request.setAttribute("amountOfPlaces", amountOfPlaces);
        request.setAttribute("dateFrom", stringDateFrom);
        request.setAttribute("dateTo", stringDateTo);
        request.setAttribute("cost", cost);
        return new ForwardAction("requestInfo.jsp");

    }
}
