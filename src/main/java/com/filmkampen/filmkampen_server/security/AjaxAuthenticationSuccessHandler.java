package com.filmkampen.filmkampen_server.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class AjaxAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private Log LOG = LogFactory.getLog(AjaxAuthenticationSuccessHandler.class);

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException,
            ServletException {
        LOG.info("####HERE XMLHTTPREquest");
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            response.getWriter().print("{success:true, targetUrl : \'" + this.getTargetUrlParameter() + "\'}");
            response.getWriter().flush();
        } else {
            super.onAuthenticationSuccess(request, response, auth);
        }
    }
}
