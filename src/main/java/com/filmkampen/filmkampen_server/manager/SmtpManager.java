package com.filmkampen.filmkampen_server.manager;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.persistence.internal.nosql.adapters.mongo.MongoJCAConnectionSpec;
import org.eclipse.persistence.nosql.adapters.mongo.MongoConnectionSpec;
import org.springframework.stereotype.Component;

import com.filmkampen.filmkampen_server.crypt.Crypto;

@Component
public class SmtpManager {
    
    private Log LOG = LogFactory.getLog(SmtpManager.class);
    
    private Properties dbProp = new Properties();
     
    public SmtpManager() {
        
        InputStream in = null;
        try {
            in = this.getClass().getClassLoader().getResourceAsStream("smtp.properties");
            dbProp.load(in);
        } catch(Exception e) {
            LOG.error("Could not load properties.", e);
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                //do nothing
            }
        }
       
    }
    
    public String getUsername() {
        return dbProp.getProperty("smtp.username");
    }
    
    public String getPassword() {
        return Crypto.decrypt(dbProp.getProperty("smtp.password"));
    }
    
    public void setPassword(String password) {
        Crypto.encrypt(password);
    }
    
    public static void main(String[] args) {
        SmtpManager smtpManager = new SmtpManager();
        System.out.println(smtpManager.getPassword());
    }
   
}
