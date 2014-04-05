package com.filmkampen.filmkampen_server.service;

import org.springframework.stereotype.Component;

import com.filmkampen.filmkampen_server.entity.User;

@Component
public class UserService extends Service<User> {
    
    public User findByUsername(String username) {
        return (User) em.createQuery("Select u from User u where u.userName = '" + username + "'").getResultList().get(0);
    } 
}
