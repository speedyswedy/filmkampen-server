package com.filmkampen.filmkampen_server.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.filmkampen.filmkampen_server.entity.User;

@Component
public class UserService extends Service<User> {

    public static void main(String[] args) {
        UserService service = new UserService();
//        User user = new User();
//        user.setFirstName("Kalle");
//        service.save(user);
        List<User> users = service.list();
        for (User tempUser : users) {
            tempUser.setLastName("Karlsson3");
            tempUser.setUserName("perkar");
            System.out.println(tempUser.getFirstName());
            service.save(tempUser);
        }
    }
}
