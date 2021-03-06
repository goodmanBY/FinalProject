package com.savko.command.admin;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.command.exception.CommandException;
import com.savko.constant.Attributes;
import com.savko.constant.Pages;
import com.savko.constant.Parameters;
import com.savko.entity.User;
import com.savko.service.ServiceException;
import com.savko.service.UserService;
import com.savko.validation.BlockDescriptionValidation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BlockDescriptionCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(BlockDescriptionCommand.class);

    @Override
    public Action execute(HttpServletRequest request) throws CommandException {
        String userId = request.getParameter(Parameters.USER_ID);
        String blockDescription = request.getParameter(Parameters.BLOCK_DESCRIPTION);
        List<User> users;
        try {
            if(BlockDescriptionValidation.isValidBlockDescription(blockDescription)) {
                UserService.getInstance().blockUser(Integer.parseInt(userId), blockDescription);
                users = UserService.getInstance().takeAllUsers();
            } else {
                request.setAttribute(Attributes.ERROR, "Fill description field");
                return new ForwardAction(Pages.ADMIN_BLOCK_DESCRIPTION);
            }
        } catch (ServiceException e) {
            LOGGER.error("Unable to block user.", e);
            throw new CommandException("Unable to block user.", e);
        }
        request.setAttribute(Attributes.USERS, users);

        return new ForwardAction(Pages.ADMIN_ALL_USERS);
    }
}
