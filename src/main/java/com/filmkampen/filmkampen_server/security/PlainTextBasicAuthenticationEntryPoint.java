package com.filmkampen.filmkampen_server.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

public class PlainTextBasicAuthenticationEntryPoint extends
        BasicAuthenticationEntryPoint {
    
    private Log LOG = LogFactory.getLog(PlainTextBasicAuthenticationEntryPoint.class);

      @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
          LOG.info("############Plain commence");
          response.addHeader("Access-Control-Allow-Origin", "*");
          //response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
          //response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//          PrintWriter writer = response.getWriter();
//          writer.println("HTTP Status " + HttpServletResponse.SC_UNAUTHORIZED + " - " + authException.getMessage());
          response.addHeader("Access-Control-Allow-Origin", "*");
          response.addHeader("Access-Control-Allow-Credentials", "true");
          //response.addHeader("Access-Control-Allow-Headers", "Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With, X-XSRF-TOKEN, X-Access-Token, Access-Control-Allow-Origin, Overrite, Destination, Depth, User-Agent, X-File-Size, X-Requested-With, If-Modified-Since, X-File-Name, accept");
          //response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, PROPFIND, PROPPATCH, COPY, MOVE, MKCOL, LOCK, GETLIB, VERSION-ONCTROL, CHECKIN, CHECKOUT, UNCHECKOUT, REPORT, UPDATE, CANCELUPLOAD, HEAD");
          response.addHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
          response.addHeader("Access-Control-Allow-Methods", "GET,POST,HEAD,OPTIONS,PUT");
          response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
      }

}
