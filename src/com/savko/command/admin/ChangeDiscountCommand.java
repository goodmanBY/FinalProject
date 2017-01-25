package com.savko.command.admin;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.command.exception.CommandException;
import com.savko.constant.Attributes;
import com.savko.constant.Pages;
import com.savko.constant.Parameters;
import com.savko.entity.Discount;
import com.savko.service.ServiceException;
import com.savko.service.SettingService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ChangeDiscountCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(ChangeDiscountCommand.class);

    @Override
    public Action execute(HttpServletRequest request) throws CommandException {
        String discountValue = request.getParameter(Parameters.DISCOUNT_VALUE);
        String discountId = request.getParameter(Parameters.DISCOUNT_ID);
        try {
            SettingService.getInstance().changeDiscountById(Integer.parseInt(discountValue), Integer.parseInt(discountId));
            List<Discount> discounts = SettingService.getInstance().takeAllDiscounts();
            int currentRoomCost = SettingService.getInstance().takeRoomCost();
            request.setAttribute(Attributes.ROOM_COST, currentRoomCost);
            request.setAttribute(Attributes.DISCOUNTS, discounts);
        } catch (ServiceException e) {
            LOGGER.error("Unable change discount value.", e);
            throw new CommandException("Unable change discount value.", e);
        }

        return new ForwardAction(Pages.ADMIN_SETTINGS);
    }
}