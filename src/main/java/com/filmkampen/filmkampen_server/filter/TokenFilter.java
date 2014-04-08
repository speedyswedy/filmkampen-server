package com.filmkampen.filmkampen_server.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TokenFilter implements Filter {
    
    private Log LOG = LogFactory.getLog(CorsFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void destroy() { }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = null;
        if (servletRequest instanceof HttpServletRequest) {
             token = ((HttpServletRequest) servletRequest).getHeader("X-Access-Token");
             LOG.info("Got token:" + token);
        }
        if (servletResponse instanceof HttpServletResponse && token != null) {
            HttpServletResponse alteredResponse = ((HttpServletResponse) servletResponse);
            LOG.info("Add token to response header");
            alteredResponse.addHeader("access_token", token);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}