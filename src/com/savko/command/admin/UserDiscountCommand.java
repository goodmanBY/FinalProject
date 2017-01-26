package com.savko.command.admin;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.command.exception.CommandException;
import com.savko.constant.Attributes;
import com.savko.constant.Pages;
import com.savko.constant.Parameters;
import com.savko.entity.Discount;
import com.savko.entity.User;
import com.savko.service.ServiceException;
import com.savko.service.SettingService;
import com.savko.service.UserService;
import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserDiscountCommand implements Command {

    private final static Logger LOGGER = Logger.getLogger(UserDiscountCommand.class);

    @Override
    public Action execute(HttpServletRequest request) throws CommandException {
        String userId = request.getParameter(Parameters.USER_ID);
        String discountId = request.getParameter(Parameters.DISCOUNT_ID);
        try {
            Discount discount = SettingService.getInstance().takeDiscountById(Integer.parseInt(discountId));
            UserService.getInstance().setUserDiscountValueByUserId(Integer.parseInt(userId), discount.getDiscountId());
            List<User> users = UserService.getInstance().takeAllUsers();
            List<Discount> discounts = SettingService.getInstance().takeAllDiscounts();
            request.setAttribute(Attributes.DISCOUNTS, discounts);
            request.setAttribute(Attributes.USERS, users);
        } catch (ServiceException e) {
            LOGGER.error("Unable set user discount.", e);
            throw new CommandException("Unable set user discount.", e);
        }

        return new ForwardAction(Pages.ADMIN_ALL_USERS);
    }
}
