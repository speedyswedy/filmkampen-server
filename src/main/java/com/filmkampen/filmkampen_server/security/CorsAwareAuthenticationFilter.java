package com.filmkampen.filmkampen_server.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CorsAwareAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private Log LOG = LogFactory.getLog(CorsAwareAuthenticationFilter.class);
    static final String ORIGIN = "Origin";

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
        LOG.info("-----Origin:" + request.getHeader(ORIGIN));
        if (request.getHeader(ORIGIN) != null) {
            String origin = request.getHeader(ORIGIN);
            response.addHeader("Access-Control-Allow-Origin", origin);
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            response.addHeader("Access-Control-Allow-Credentials", "true");
            response.addHeader("Access-Control-Allow-Headers",
                    request.getHeader("Access-Control-Request-Headers"));
        }
        LOG.info("------Method:" + request.getMethod());
        if (request.getMethod() == "OPTIONS") {
            try {
                response.getWriter().print("OK");
                response.getWriter().flush();
            } catch (IOException e) {
                
            }
            return null;
        }
        return super.attemptAuthentication(request, response);
    }
}
