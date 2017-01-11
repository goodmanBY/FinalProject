package com.savko.command;

import com.savko.command.admin.AdminLogInCommand;
import com.savko.command.admin.AdminLogOutCommand;
import com.savko.command.admin.AllBookingRequestsCommand;
import com.savko.command.admin.AllUsersCommand;
import com.savko.command.client.*;
import com.savko.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class CommandHolder {

    private static CommandHolder instance = new CommandHolder();
    private HashMap<String, Command> commands = new HashMap<>();

    private CommandHolder() {

        commands.put("userRegistration", new UserRegistrationCommand());
        commands.put("userLogIn", new UserLogInCommand());
        commands.put("userLogOut", new UserLogOutCommand());
        commands.put("changeLanguage", new LanguageChangeCommand());
        commands.put("adminLogIn", new AdminLogInCommand());
        commands.put("adminLogOut", new AdminLogOutCommand());
        commands.put("allUsers", new AllUsersCommand());
        commands.put("userRequest", new UserRequestCommand());
        commands.put("userRequestInfo", new UserRequestInfoCommand());
        commands.put("userAllRequestsInfo", new UserAllRequestsCommand());
        commands.put("preparePayRequest", new PreparePayRequestCommand());
        commands.put("payRequest", new PayRequestCommand());
        commands.put("blockUser", new BlockUserCommand());
        commands.put("unblockUser", new UnblockUserCommand());
        commands.put("allBookingRequests", new AllBookingRequestsCommand());
        commands.put("confirmBookingRequest", new ConfirmBookingRequestCommand());
        commands.put("cancelConfirmation", new CancelConfirmationCommand());

    }

    public static CommandHolder getInstance() {
        return instance;
    }


    public Command getCommand(HttpServletRequest request) throws CommandException {

        String commandName = request.getParameter("action");

        if (!commands.containsKey(commandName)) {
            throw new CommandException("No such command: " + commandName);
        }

        return commands.get(commandName);
    }

}