package com.filmkampen.filmkampen_server.resource;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.UUID;

import javax.annotation.Resource;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.filmkampen.filmkampen_server.entity.User;
import com.filmkampen.filmkampen_server.service.UserService;
import com.sun.jersey.core.util.Base64;

@Path("/login")
@Component
@Produces("application/json")
public class LoginResource {
    
    private Log LOG = LogFactory.getLog(LoginResource.class);

    @Resource
    private UserService userService;
    
    public String decode(String s) {
        try {
            return new String(Base64.decode(s), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOG.error("Could not convert string to base 64.", e);
        }
        return "";
    }

//    @POST
//    public Response login(Credential credential) {
//        
//        credential.setToken(null);
//        LOG.info(credential.getUsername());
//        User user = userService.findByUsername(credential.getUsername());
//        if (user != null) {
//            String password = user.getPassword();
//            
//            LOG.info("###############password:" + password);
//            LOG.info("###############cred password:" + decode(credential.getPassword()));
//            if (password != null && password.equals(decode(credential.getPassword()))) {
//                Calendar cal = Calendar.getInstance();
//                String token = UUID.randomUUID().toString().toUpperCase() 
//                        + "|" + user.getUserName() + "|"
//                        + cal.getTimeInMillis();;
//                credential.setToken(token);
//                LOG.info("Token is returned.");
//            }
//        }
//        
//        if (credential.getToken() == null) {
//            return Response.status(401).build();
//        }
//        return Response.ok().entity(credential).build();
//    }
    
    @POST
    public Response login() {
//        LOG.info("#########Cred:" + decode(credentials));
//        String usernameAndPassword = decode(credentials);
//        String username = usernameAndPassword.split(":")[0];
//        String password = usernameAndPassword.split(":")[0];
//        
//        User user = userService.findByUsername(username);
//        boolean userExist = false;
//        if (user != null) {
//            if (password != null && password.equals(user.getPassword())) {
//                userExist = true;
//            }
//        }
//  
//        if (!userExist) {
//            return Response.status(401).build();
//        }
        
        return Response.ok().build();
    }
    
}
