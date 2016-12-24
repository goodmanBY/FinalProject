package com.savko.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectAction extends Action {

    public RedirectAction(String path) {
        super(path);
    }

    @Override
    public void go(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(path);
    }
}