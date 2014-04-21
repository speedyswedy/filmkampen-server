package com.filmkampen.filmkampen_server.resource;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.filmkampen.filmkampen_server.entity.User;
import com.filmkampen.filmkampen_server.service.UserService;

@Path("/newAccount")
@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)   
public class NewAccountResource {
    
    private Log LOG = LogFactory.getLog(NewAccountResource.class);

    @Resource
    private UserService userService;
    
    @POST
    public @ResponseBody Response createUser(User user) {
        LOG.info("############Create User:" + user.getUserName());
        User existingUser = userService.findByUsername(user.getUserName());
        if (existingUser == null) {
            StandardPasswordEncoder encoder = new StandardPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            user = (User) userService.save(user);
        } else {
            LOG.info("############Return NULL");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        LOG.info("############Return USER");
        return Response.ok(user, MediaType.APPLICATION_JSON).entity(user).build();
    }

}
