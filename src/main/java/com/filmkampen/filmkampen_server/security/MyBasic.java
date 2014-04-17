package com.filmkampen.filmkampen_server.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class MyBasic extends BasicAuthenticationFilter {

    private Log LOG = LogFactory.getLog(MyBasic.class);
    
    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult)
            throws IOException {
        LOG.info("###### onSuccessfulAuthentication");
        response.getWriter().print(
                "{success:true, targetUrl : \'"
                        + this.getServletContext().getRealPath("/home") + "\'}");
        response.getWriter().flush();
    }

}
