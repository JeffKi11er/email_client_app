package com.example.email_client_app.item;

import java.sql.Time;

public class ItemEmail {
    private String name;
    private String date;
    private int imgProfile;
    private boolean starred;
    private String subject;
    private String description;
    private boolean important;
    private String snoozed;
    private String label;
    private boolean ads;

    public ItemEmail(String name,
                     String date,
                     int imgProfile,
                     boolean starred,
                     String subject,
                     String description,
                     boolean important,
                     String snoozed,
                     String label,
                     boolean ads) {
        this.name = name;
        this.date = date;
        this.imgProfile = imgProfile;
        this.starred = starred;
        this.subject = subject;
        this.description = description;
        this.important = important;
        this.snoozed = snoozed;
        this.label = label;
        this.ads = ads;
    }

    public String getSnoozed() {
        return snoozed;
    }

    public void setSnoozed(String snoozed) {
        this.snoozed = snoozed;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isAds() {
        return ads;
    }

    public void setAds(boolean ads) {
        this.ads = ads;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
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
