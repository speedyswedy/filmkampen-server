package com.filmkampen.filmkampen_server.resource;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.filmkampen.filmkampen_server.entity.User;
import com.filmkampen.filmkampen_server.service.MovieService;
import com.filmkampen.filmkampen_server.service.UserService;

@Path("/user")
@Component
@Produces("application/json")
public class UserResource {
    
    private Log LOG = LogFactory.getLog(UserResource.class);

    @Resource
    private UserService userService;

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
    public void createUser(User user) {
        userService.save(user);
    }

    @DELETE
    public void remove(User user) {
        userService.remove(user.getId());
    }
    
    @POST
    @Path("/resetPassword")
    public void resetPassword(String email) {
        LOG.info("############" + email);
    }
}
