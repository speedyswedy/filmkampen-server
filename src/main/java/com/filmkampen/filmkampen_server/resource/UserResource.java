package com.filmkampen.filmkampen_server.resource;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.filmkampen.filmkampen_server.entity.User;
import com.filmkampen.filmkampen_server.service.MovieService;
import com.filmkampen.filmkampen_server.service.UserService;

@Path("/user")
@Component
@Produces("application/json")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @GET
    public List<User> getUsers() {
        return userService.list();
    }

    @GET
    public User find(String id) {
        return (User) userService.find(id);
    }
    
    @GET
    public User findByUsername(String username) {
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
}
