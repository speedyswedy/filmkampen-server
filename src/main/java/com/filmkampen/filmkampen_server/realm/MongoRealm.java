package com.filmkampen.filmkampen_server.realm;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.catalina.realm.GenericPrincipal;
import org.apache.catalina.realm.RealmBase;
import org.springframework.stereotype.Service;

import com.filmkampen.filmkampen_server.service.UserService;

@Service
public class MongoRealm {//extends RealmBase {
    
//    @Resource
//    private UserService userService;
//
//    @Override
//    protected String getName() {
//        return this.getClass().getSimpleName();
//    }
//
//    @Override
//    protected String getPassword(final String username) {
//        System.out.println("getPassword:" + userService);
//        return userService.getPasswordByUsername(username);
//    }
//
//    @Override
//    protected Principal getPrincipal(final String username) {
//        String name = "ADMIN";
//        final List<String> roles = new ArrayList<String>();
//        roles.add(name);
//        String password = getPassword(username);
//        return new GenericPrincipal(username, password, roles);
//    }
}
