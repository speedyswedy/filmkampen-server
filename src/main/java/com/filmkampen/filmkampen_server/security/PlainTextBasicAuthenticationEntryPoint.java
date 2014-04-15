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
          response.addHeader("Access-Control-Allow-Credentials", "true");
          response.addHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
          response.addHeader("Access-Control-Allow-Methods", "GET,POST,HEAD,PUT");
          //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
      }

}
