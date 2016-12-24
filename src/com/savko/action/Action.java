package com.savko.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Action {

    protected String path;

    public Action(String path) {
        this.path = path;
    }

    public abstract void go(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}