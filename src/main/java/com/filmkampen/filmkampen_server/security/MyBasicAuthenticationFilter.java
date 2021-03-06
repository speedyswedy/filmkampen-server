package com.filmkampen.filmkampen_server.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class MyBasicAuthenticationFilter extends BasicAuthenticationFilter {

    private Log LOG = LogFactory.getLog(MyBasicAuthenticationFilter.class);

    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult)
            throws IOException {
       
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter writer = response.getWriter();
            writer.println("OK");
            writer.flush();
            LOG.info("Returning OK...");
           super.onSuccessfulAuthentication(request, response, authResult);

    }

}
