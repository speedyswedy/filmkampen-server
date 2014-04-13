package com.filmkampen.filmkampen_server.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CorsFilter implements Filter {
    
    private Log LOG = LogFactory.getLog(CorsFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void destroy() { }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletResponse instanceof HttpServletResponse) {
            HttpServletResponse alteredResponse = ((HttpServletResponse) servletResponse);
            LOG.info("Add CORS header");
            addHeadersFor200Response(alteredResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
   
    private void addHeadersFor200Response(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        //response.addHeader("Access-Control-Allow-Headers", "Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With, X-XSRF-TOKEN, X-Access-Token, Access-Control-Allow-Origin, Overrite, Destination, Depth, User-Agent, X-File-Size, X-Requested-With, If-Modified-Since, X-File-Name, accept");
        //response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, PROPFIND, PROPPATCH, COPY, MOVE, MKCOL, LOCK, GETLIB, VERSION-ONCTROL, CHECKIN, CHECKOUT, UNCHECKOUT, REPORT, UPDATE, CANCELUPLOAD, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
        response.addHeader("Access-Control-Allow-Methods", "GET,POST,HEAD,PUT");
    
    }
}