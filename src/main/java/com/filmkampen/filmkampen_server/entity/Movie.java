package com.filmkampen.filmkampen_server.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Temporal;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.NoSql;

@Entity
@NoSql(dataFormat=DataFormatType.MAPPED)
public class Movie extends BaseEntity implements Serializable {
  
    private static final long serialVersionUID = -2894460335097481186L;

    public Movie() { 
    }
    
    @Basic
    private String title;
    
    @Basic
    private String description;
    
    @Basic
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date publishDate;
    
    @Basic
    private String director;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
