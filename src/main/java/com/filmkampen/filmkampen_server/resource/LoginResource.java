package com.filmkampen.filmkampen_server.resource;

import javax.annotation.Resource;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.filmkampen.filmkampen_server.entity.Credential;
import com.filmkampen.filmkampen_server.entity.User;
import com.filmkampen.filmkampen_server.manager.PersistenceManager;
import com.filmkampen.filmkampen_server.service.UserService;

@Path("/login")
@Component
@Produces("application/json")
public class LoginResource {
    
    private Log LOG = LogFactory.getLog(LoginResource.class);

    @Resource
    private UserService userService;

    @POST
    public Credential login(Credential credential) {
        credential.setToken(null);
        User user = userService.findByUsername(credential.getUsername());
        if (user != null) {
            String password = user.getPassword();
            if (password != null && password.equals(credential.getPassword())) {
                String token = "123456qwert";
                credential.setToken(token);
                LOG.info("Token is returned.");
            }
        }
        return credential;
    }

    
    
}