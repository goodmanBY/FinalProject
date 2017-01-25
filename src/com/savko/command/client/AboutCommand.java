package com.savko.command.client;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.command.exception.CommandException;
import com.savko.constant.Attributes;
import com.savko.constant.Pages;
import com.savko.service.ServiceException;
import com.savko.service.SettingService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AboutCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(AboutCommand.class);

    @Override
    public Action execute(HttpServletRequest request) throws CommandException {
        try {
            int roomCost = SettingService.getInstance().takeRoomCost();
            request.setAttribute(Attributes.ROOM_COST, roomCost);
        } catch (ServiceException e) {
            LOGGER.error("Unable to take room cost.", e);
            throw new CommandException("Unable to take room cost.", e);
        }

        return new ForwardAction(Pages.ABOUT);
    }
}
