package com.savko.command.admin;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.constant.Attributes;
import com.savko.constant.Pages;
import com.savko.constant.Parameters;
import com.savko.dao.AdminDao;
import com.savko.dao.DaoException;
import com.savko.entity.Admin;
import com.savko.util.HashUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminLogInCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(AdminLogInCommand.class);

    @Override
    public Action execute(HttpServletRequest request) {
        String login = request.getParameter(Parameters.LOGIN);
        String password = HashUtil.getMd5Hash(request.getParameter(Parameters.PASSWORD));
        HttpSession session = request.getSession();
        AdminDao adminDao = new AdminDao();
        try {
            if (adminDao.checkAdmin(login, password)) {
                Admin admin = new Admin()
                        .setLogin(login)
                        .setPassword(password);
                session.setAttribute(Attributes.ADMIN, admin);
                return new ForwardAction(Pages.ADMIN_CONTROL_PANEL);
            } else {
                request.setAttribute(Attributes.ERROR, "You are not admin");
                return new ForwardAction(Pages.ADMIN_INDEX);
            }
        } catch (DaoException e) {
            LOGGER.error("Unable to log in admin.", e);
        }
        return null;
    }
}
