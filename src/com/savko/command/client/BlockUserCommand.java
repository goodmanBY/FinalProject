package com.savko.command.client;

import com.savko.action.Action;
import com.savko.action.ForwardAction;
import com.savko.command.Command;
import com.savko.constant.Attributes;
import com.savko.constant.Pages;
import com.savko.constant.Parameters;

import javax.servlet.http.HttpServletRequest;

public class BlockUserCommand implements Command {

    @Override
    public Action execute(HttpServletRequest request) {
        String userId = request.getParameter(Parameters.USER_ID);
        request.setAttribute(Attributes.USER_ID, userId);
        return new ForwardAction(Pages.ADMIN_BLOCK_DESCRIPTION);
    }
}
