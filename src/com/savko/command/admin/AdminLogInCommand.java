package com.savko.command.admin;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.command.exception.CommandException;
import com.savko.constant.Attributes;
import com.savko.constant.Pages;
import com.savko.constant.Parameters;
import com.savko.entity.Admin;
import com.savko.service.AdminService;
import com.savko.service.ServiceException;
import com.savko.util.HashUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminLogInCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(AdminLogInCommand.class);

    @Override
    public Action execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter(Parameters.LOGIN);
        String password = HashUtil.getMd5Hash(request.getParameter(Parameters.PASSWORD));
        HttpSession session = request.getSession();
        try {
            if (AdminService.getInstance().checkAdmin(login, password)) {
                Admin admin = new Admin()
                        .setLogin(login)
                        .setPassword(password);
                session.setAttribute(Attributes.ADMIN, admin);
                return new ForwardAction(Pages.ADMIN_CONTROL_PANEL);
            } else {
                request.setAttribute(Attributes.ERROR, "You are not admin");
                return new ForwardAction(Pages.ADMIN_INDEX);
            }
        } catch (ServiceException e) {
            //LOGGER.error("Unable to log in admin.", e);
            throw new CommandException("Unable to log in admin.", e);
        }
    }
}
