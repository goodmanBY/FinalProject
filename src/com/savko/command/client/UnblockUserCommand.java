package com.savko.command.client;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.constant.Pages;
import com.savko.constant.Parameters;
import com.savko.dao.DaoException;
import com.savko.dao.UserDao;
import com.savko.service.ServiceException;
import com.savko.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UnblockUserCommand implements Command {
    private final static Logger LOGGER = Logger.getLogger(UnblockUserCommand.class);

    @Override
    public Action execute(HttpServletRequest request) {
        String userId = request.getParameter(Parameters.USER_ID);
        try {
            UserService.getInstance().unblockUser(Integer.parseInt(userId));
        } catch (ServiceException e) {
            LOGGER.error("Unable to update table 'client'.", e);
        }
        return new ForwardAction(Pages.ADMIN_CONTROL_PANEL);
    }
}
