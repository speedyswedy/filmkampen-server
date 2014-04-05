package com.filmkampen.filmkampen_server.resource;

import static org.junit.Assert.fail;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.filmkampen.filmkampen_server.entity.User;

@ContextConfiguration("file:src/main/webapp/WEB-INF/dispatcher-servlet.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserResourceTest {
    
    @Resource
    private UserResource userResource;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testGetUsers() {
        List<User> users = userResource.getUsers();
        for (User user : users) {
            System.out.println("User:" + user.getFirstName());
        }
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setFirstName("Ingrid");
        user.setLastName("Larsson");
        user.setUserName("inge");
        userResource.createUser(user);
    }
    
    @Test
    public void findUserByUserName() {
        User user = userResource.findByUsername("inge");
        System.out.println("User:" + user.getUserName());
    }

    @Test
    public void testRemove() {
        
        
    }

}
