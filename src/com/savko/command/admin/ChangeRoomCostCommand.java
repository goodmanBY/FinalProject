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
import com.savko.validation.SettingsValidation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ChangeRoomCostCommand implements Command{

    private final static Logger LOGGER = Logger.getLogger(ChangeRoomCostCommand.class);

    @Override
    public Action execute(HttpServletRequest request) throws CommandException {
        String roomCost = request.getParameter(Parameters.ROOM_COST);
        try {
            List<Discount> discounts = SettingService.getInstance().takeAllDiscounts();
            request.setAttribute(Attributes.DISCOUNTS, discounts);
            int currentRoomCost = SettingService.getInstance().takeRoomCost();
            if(SettingsValidation.isRoomCostValid(roomCost)) {
                SettingService.getInstance().changeRoomCost(Integer.parseInt(roomCost));
                int changedRoomCost = SettingService.getInstance().takeRoomCost();
                request.setAttribute(Attributes.ROOM_COST, changedRoomCost);
            } else {
                request.setAttribute(Attributes.ROOM_COST, currentRoomCost);
                request.setAttribute(Attributes.ERROR, "Fill the field");
            }
        } catch (ServiceException e) {
            LOGGER.error("Unable to change room cost.", e);
            throw new CommandException("Unable to change room cost.", e);
        }
        return new ForwardAction(Pages.ADMIN_SETTINGS);
    }
}
