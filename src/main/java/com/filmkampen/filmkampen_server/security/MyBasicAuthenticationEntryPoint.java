package com.filmkampen.filmkampen_server.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

public class MyBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
    
    private Log LOG = LogFactory.getLog(MyBasicAuthenticationEntryPoint.class);

      @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
              response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
              response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
              PrintWriter writer = response.getWriter();
              writer.println("HTTP Status 401 - " + authException.getMessage());
              writer.flush();
              LOG.info("HTTP AJAX Status 401 - " + authException.getMessage());
          }  else {
              LOG.info("HTTP Status 401 - " + authException.getMessage());
              super.commence(request, response, authException);
          }
      }
      
      @Override
      public void afterPropertiesSet() throws Exception {
          setRealmName("filmkampen.com");
          super.afterPropertiesSet();
      }

}
