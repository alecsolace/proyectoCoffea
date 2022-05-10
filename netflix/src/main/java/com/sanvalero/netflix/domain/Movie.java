package com.sanvalero.netflix.domain;

/**
 * Una película
 */
public class Movie {
    
    private int id;
    private String title;
    private String director;
    private int duration;
    private String category;
    private boolean viewed;
   
    public Movie(int id, String title, String director, int duration, String category, boolean viewed) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.duration = duration;
        this.category = category;
        this.viewed = viewed;
    }

    public Movie(String title, String director, int duration, String category, boolean viewed) {
        this.title = title;
        this.director = director;
        this.duration = duration;
        this.category = category;
        this.viewed = viewed;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }
    
    
}
