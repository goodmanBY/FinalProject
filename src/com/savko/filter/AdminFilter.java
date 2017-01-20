package com.savko.filter;

import com.savko.constant.Attributes;
import com.savko.constant.Pages;
import com.savko.entity.Admin;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        Admin admin = (Admin) session.getAttribute(Attributes.ADMIN);
        if (admin != null) {
            filterChain.doFilter(request, response);
        } else {
            request.getRequestDispatcher(Pages.ADMIN_INDEX).forward(request, response);
        }
    }

    public void destroy() {

    }
}
