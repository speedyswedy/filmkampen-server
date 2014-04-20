package com.filmkampen.filmkampen_server.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.NoSql;

@XmlRootElement
@Entity
@NoSql(dataFormat=DataFormatType.MAPPED)
public class User extends BaseEntity implements Serializable {
   
    private static final long serialVersionUID = 8180315875768851194L;

    public User() { 
    }
    
    @Basic
    private String firstName;
    
    @Basic
    private String lastName;
    
    @Basic
    private String userName;
    
    @Basic
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
