package com.example.email_client_app.item;

public class ItemEmail {
    private String name;
    private String date;
    private int imgProfile;
    private boolean starred;
    private String subject;
    private String description;

    public ItemEmail(String name, String date, int imgProfile, boolean starred, String subject,String description) {
        this.name = name;
        this.date = date;
        this.imgProfile = imgProfile;
        this.starred = starred;
        this.subject = subject;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImgProfile() {
        return imgProfile;
    }

    public void setImgProfile(int imgProfile) {
        this.imgProfile = imgProfile;
    }

    public boolean isStarred() {
        return starred;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
