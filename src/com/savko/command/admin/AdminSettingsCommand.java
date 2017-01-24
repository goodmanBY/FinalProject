package com.savko.command.admin;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.command.exception.CommandException;
import com.savko.constant.Pages;
import com.savko.entity.Discount;
import com.savko.service.ServiceException;
import com.savko.service.SettingService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminSettingsCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(AdminLogInCommand.class);

    @Override
    public Action execute(HttpServletRequest request) throws CommandException {
        try {
            int currentRoomCost = SettingService.getInstance().takeRoomCost();
            List<Discount> discounts = SettingService.getInstance().takeAllDiscounts();
            request.setAttribute("roomCost", currentRoomCost);
            request.setAttribute("discounts", discounts);
        } catch (ServiceException e) {
            LOGGER.error("Unable to take discounts or room cost.", e);
            throw new CommandException("Unable to take discounts or room cost.", e);
        }

        return new ForwardAction(Pages.ADMIN_SETTINGS);
    }
}
