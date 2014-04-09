package com.filmkampen.filmkampen_server.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.filmkampen.filmkampen_server.entity.User;

@Component
public class UserService extends Service<User> implements UserDetailsService {
    
    public User findByUsername(String username) {
        return (User) em.createQuery("Select u from User u where u.userName = '" + username + "'").getResultList().get(0);
    }
    
    public String getPasswordByUsername(String username) {
        User user =  (User) em.createQuery("Select u from User u where u.userName = '" + username + "'").getResultList().get(0);
        return user.getPassword();
    }
   
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Getting access details from employee dao !!");
        
        User user = findByUsername(username);
        
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>(Arrays.asList(new SimpleGrantedAuthority[]{ new SimpleGrantedAuthority("ROLE_USER") }));
        
        UserDetails userDetail = new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), true, true, true, true, authorities);
        return userDetail;
    }
}
