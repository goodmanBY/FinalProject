package com.savko.controller;

import com.savko.action.Action;
import com.savko.command.Command;
import com.savko.command.CommandHolder;
import com.savko.command.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(Controller.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * @param request  HttpServletRequest instance
     * @param response HttpServletResponse instance
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CommandHolder requestHelper = CommandHolder.getInstance();

        Command command = requestHelper.getCommand(request);
        Action action = command.execute(request);
        action.go(request, response);

    }

    @Override
    public void destroy() {
        super.destroy();
    }
}