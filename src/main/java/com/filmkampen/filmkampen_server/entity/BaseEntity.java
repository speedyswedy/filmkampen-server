package com.filmkampen.filmkampen_server.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.eclipse.persistence.nosql.annotations.Field;

@MappedSuperclass
public class BaseEntity {
    
    @Id
    @GeneratedValue
    @Field(name="_id")
    private String id;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
