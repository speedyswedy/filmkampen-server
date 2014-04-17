package com.filmkampen.filmkampen_server.resource;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

@Path("/login")
@Component
@Produces("application/json")
public class LoginResource {
    
    private Log LOG = LogFactory.getLog(LoginResource.class);    
  
    @POST
    public @ResponseBody Response login() {        
        return Response.ok().build();
    }
    
}
