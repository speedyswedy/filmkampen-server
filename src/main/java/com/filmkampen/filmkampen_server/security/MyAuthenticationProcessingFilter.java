package com.filmkampen.filmkampen_server.security;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
 
public class MyAuthenticationProcessingFilter extends UsernamePasswordAuthenticationFilter {
 
    private Log LOG = LogFactory.getLog(MyAuthenticationProcessingFilter.class);
    
    protected void onSuccessfulAuthentication(HttpServletRequest request,
            HttpServletResponse response, Authentication authResult)
    throws IOException {
        LOG.info("###### onSuccessfulAuth");
        response.getWriter().print(
                "{success:true, targetUrl : \'"
                        + this.getFilterProcessesUrl() + "\'}");
        response.getWriter().flush();
 
    }
 
    protected void onUnsuccessfulAuthentication( HttpServletRequest request,
            HttpServletResponse response, AuthenticationException failed )
    throws IOException {
 
        LOG.info("###### onUnsuccessfulAuth");
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);
 
        Writer out = responseWrapper.getWriter();
 
        out.write("{ success: false, errors: { reason: 'Login failed. Try again.' }}");
        out.close();
 
    }
 
}
