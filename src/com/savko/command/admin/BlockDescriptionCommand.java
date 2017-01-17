package com.savko.command.admin;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.constant.Pages;
import com.savko.constant.Parameters;
import com.savko.service.ServiceException;
import com.savko.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class BlockDescriptionCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(BlockDescriptionCommand.class);

    @Override
    public Action execute(HttpServletRequest request) {
        String userId = request.getParameter(Parameters.USER_ID);
        String blockDescription = request.getParameter("blockDescription");
        try {
            UserService.getInstance().blockUser(Integer.parseInt(userId));
            UserService.getInstance().addBlockDescription(Integer.parseInt(userId), blockDescription);
        } catch (ServiceException e) {
            LOGGER.error("Unable to update table 'client'", e);
        }
        return new ForwardAction(Pages.ADMIN_CONTROL_PANEL);
    }
}