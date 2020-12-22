package com.example.email_client_app.item;

public class ItemSentEmail {
    private String name;
    private String date;
    private int imgProfile;
    private boolean starred;
    private String subject;
    private String description;
    private int numofmail;

    public ItemSentEmail(String name, String date, int imgProfile, boolean starred, String subject,String description,int numofmail) {
        this.name = name;
        this.date = date;
        this.imgProfile = imgProfile;
        this.starred = starred;
        this.subject = subject;
        this.description = description;
        this.numofmail = numofmail;
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

    public int getNumofmail() { return numofmail;}

    public void setNumofmail(int numofmail) { this.numofmail = numofmail; }
}

