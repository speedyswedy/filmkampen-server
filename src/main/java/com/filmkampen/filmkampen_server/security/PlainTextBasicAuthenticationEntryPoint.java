package com.filmkampen.filmkampen_server.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

public class PlainTextBasicAuthenticationEntryPoint extends
        BasicAuthenticationEntryPoint {

      @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
          response.addHeader("Access-Control-Allow-Origin", "*");
          response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//          PrintWriter writer = response.getWriter();
//          writer.println("HTTP Status " + HttpServletResponse.SC_UNAUTHORIZED + " - " + authException.getMessage());
          response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
      }

}
