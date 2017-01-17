package com.savko.command.client;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.constant.Attributes;
import com.savko.constant.Pages;
import com.savko.constant.Parameters;
import com.savko.dao.BookingDao;
import com.savko.dao.DaoException;
import com.savko.entity.BookingRequest;
import com.savko.service.BookingService;
import com.savko.service.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class PreparePayRequestCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(PreparePayRequestCommand.class);

    @Override
    public Action execute(HttpServletRequest request) {
        String requestId = request.getParameter(Parameters.REQUEST_ID);
        request.setAttribute(Attributes.REQUEST_ID, requestId);
        try {
            BookingRequest bookingRequest = BookingService.getInstance().takeBookingRequestByRequestId(Integer.parseInt(requestId));
            request.setAttribute(Attributes.AMOUNT_OF_PLACES, bookingRequest.getAmountOfPlaces());
            request.setAttribute(Attributes.DATE_FROM, bookingRequest.getDateFrom());
            request.setAttribute(Attributes.DATE_TO, bookingRequest.getDateTo());
            request.setAttribute(Attributes.COST, bookingRequest.getCost());
        } catch (ServiceException e) {
            LOGGER.error("Unable to take request from DB.", e);
        }
        return new ForwardAction(Pages.PAY_REQUEST);
    }
}
