package com.filmkampen.filmkampen_server.resource;

import javax.annotation.Resource;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.filmkampen.filmkampen_server.entity.User;
import com.filmkampen.filmkampen_server.service.UserService;

@Path("/newAccount")
@Component
@Produces("application/json")
public class NewAccountResource {
    
    private Log LOG = LogFactory.getLog(NewAccountResource.class);

    @Resource
    private UserService userService;
    
    @POST
    public void createUser(User user) {
        LOG.info("############create User:" + user.getUserName());
        userService.save(user);
    }

}