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

public class PersistenceManager {
    
    private Log LOG = LogFactory.getLog(PersistenceManager.class);
    
    private static PersistenceManager instance = null;
    private static Object lock                 = new Object();
    private EntityManagerFactory factory;
    
    
    public PersistenceManager() {
        Properties dbProp=new Properties();
        InputStream in = null;
        try {
            in = this.getClass().getClassLoader().getResourceAsStream("dbConnection.properties");
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
        
        MongoJCAConnectionSpec mongoSpec = new MongoJCAConnectionSpec();
        mongoSpec.setUser(dbProp.getProperty("mongodb.username"));
        mongoSpec.setPassword(dbProp.getProperty("mongodb.password").toCharArray());
         
        MongoConnectionSpec spec = new MongoConnectionSpec();
        spec.setConnectionSpec(mongoSpec);
        Map<String, Object> prop = new HashMap<String, Object>();
        prop.put("eclipselink.nosql.connection-spec", spec);
        
        factory = Persistence.createEntityManagerFactory("filmkampenMongo", prop);
        
        LOG.info("Persistence Manager connected");
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
