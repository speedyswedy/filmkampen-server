package com.filmkampen.filmkampen_server.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eclipse.persistence.internal.nosql.adapters.mongo.MongoJCAConnectionSpec;
import org.eclipse.persistence.nosql.adapters.mongo.MongoConnectionSpec;

public class PersistenceManager {
    
    private static PersistenceManager instance = null;
    private static Object lock                 = new Object();
    private EntityManagerFactory factory;
    
    
    public PersistenceManager() {
        Properties dbProp=new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream(System.getProperty("WEB-INF/dbConnection.properties"));
            dbProp.load(in);
        } catch(Exception e) {
            System.err.println("e:" + e);
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                //do nothing
            }
        }
        
        MongoJCAConnectionSpec mongoSpec = new MongoJCAConnectionSpec();
        mongoSpec.setUser("perkar");
        mongoSpec.setPassword("supermongo".toCharArray());
        //mongoSpec.setUser(dbProp.getProperty("mongodb.username"));
        //mongoSpec.setPassword(dbProp.getProperty("mongodb.password").toCharArray());
         
        MongoConnectionSpec spec = new MongoConnectionSpec();
        spec.setConnectionSpec(mongoSpec);
        Map<String, Object> prop = new HashMap<String, Object>();
        prop.put("eclipselink.nosql.connection-spec", spec);
        
        
        factory = Persistence.createEntityManagerFactory("filmkampenMongo", prop);
    }
    
    public static PersistenceManager instance(){
        
        if(instance == null) { 
            synchronized (lock) {
                if(null == instance){
                    instance = new PersistenceManager();
                }
            }
        }
        return instance;
    }

    public EntityManagerFactory getFactory() {
        return factory;
    }
    
    
    public void printThis() {
        System.out.println(this);
    }
    
}
