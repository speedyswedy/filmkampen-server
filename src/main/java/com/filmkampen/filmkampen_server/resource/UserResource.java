package com.filmkampen.filmkampen_server.resource;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.filmkampen.filmkampen_server.entity.User;
import com.filmkampen.filmkampen_server.service.MovieService;
import com.filmkampen.filmkampen_server.service.SmtpService;
import com.filmkampen.filmkampen_server.service.UserService;

@Path("/user")
@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)   
public class UserResource {
    
    private Log LOG = LogFactory.getLog(UserResource.class);

    @Resource
    private UserService userService;
    
    @Resource
    private SmtpService smtpService;

    @Resource
    private MovieService movieService;

    @GET
    public List<User> getUsers() {
        return userService.list();
    }

    @GET
    @Path("/{id}")
    public User find(@PathParam("id") String id) {
        return (User) userService.find(id);
    }
    
    @GET
    @Path("/findUserByUsername/{username}")
    public User findByUsername(@PathParam("username") String username) {
        return (User) userService.findByUsername(username);
    }

    @POST
    public @ResponseBody Response createUser(User user) {
        LOG.info("############create User:" + user.getUserName());
        User existingUser = findByUsername(user.getUserName());
        LOG.info("############Existing User:" + existingUser);
        if (existingUser == null || existingUser.getUserName() == null) {
            userService.save(user);
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();   
        }
        return Response.ok().build();
    }

    @DELETE
    public void remove(User user) {
        userService.remove(user.getId());
    }
    
    @POST
    @Path("/resetPassword")
    public void resetPassword(String email) {
        LOG.info("############" + email);
        try {
            smtpService.sendMail(email);
        } catch (Exception e) {
            LOG.error("Could not send mail to " + email);
        }
    }
}
