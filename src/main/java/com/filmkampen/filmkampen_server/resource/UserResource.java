package com.filmkampen.filmkampen_server.resource;

import java.util.List;

import javax.ws.rs.GET;
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
    
    public UserResource() {
        System.out.println("########Init" + this);
    }
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private MovieService movieService; 
   
    @GET
    public List<User> getUsers() {
        System.out.println("UserService:" + userService);
        return userService.list();
    }
}
