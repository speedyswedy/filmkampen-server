package com.filmkampen.filmkampen_server.service;

import org.springframework.stereotype.Component;

import com.filmkampen.filmkampen_server.entity.User;

@Component
public class UserService extends Service<User> {
    
    public User findByUsername(String username) {
        return (User) em.createQuery("Select u from USER u where u.USERNAME = '" + username + "'").getResultList().get(0);
    }
    
    public String getPasswordByUsername(String username) {
        User user =  (User) em.createQuery("Select u from USER u where u.USERNAME = '" + username + "'").getResultList().get(0);
        return user.getPassword();
    } 
}
