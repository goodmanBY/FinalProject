package com.savko.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private final String ENCODING = "utf-8";

    public void doFilter(ServletRequest request,
                         ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding(ENCODING);
        filterChain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void destroy() {

    }

}
