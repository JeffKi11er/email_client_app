package com.example.email_client_app.item;

public class ItemSchedule {
    private String emailReceived;
    private String timeReceived;
    private int imgAvatar;
    private boolean starred;
    private String subjectSent;
    private String descriptionSchedule;
    private int imgAttachedSchedule;
    private String textAttachedSchedule;
    private String timeSend;
    private int imgStarSchedule;

    public ItemSchedule(String emailReceived, String timeReceived, int imgAvatar, boolean starred, String subjectSent, String descriptionSchedule, int imgAttachedSchedule, String timeSend, String textAttachedSchedule, int imgStarSchedule) {
        this.emailReceived = emailReceived;
        this.timeReceived = timeReceived;
        this.imgAvatar = imgAvatar;
        this.starred = starred;
        this.subjectSent = subjectSent;
        this.descriptionSchedule = descriptionSchedule;
        this.timeSend = timeSend;
        this.imgAttachedSchedule = imgAttachedSchedule;
        this.textAttachedSchedule = textAttachedSchedule;
        this.imgStarSchedule = imgStarSchedule;
    }

    public String getEmailReceived() {
        return emailReceived;
    }

    public void setEmailReceived(String emailReceived) {
        this.emailReceived = emailReceived;
    }

    public String getTimeReceived() {
        return timeReceived;
    }

    public void setTimeReceived(String timeReceived) {
        this.timeReceived = timeReceived;
    }

    public int getImgAvatar() {
        return imgAvatar;
    }

    public void setImgAvatar(int imgAvatar) {
        this.imgAvatar = imgAvatar;
    }

    public boolean isStarred() {
        return starred;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
    }

    public String getSubjectSent() {
        return subjectSent;
    }

    public void setSubjectSent(String subjectSent) {
        this.subjectSent = subjectSent;
    }

    public String getDescriptionSchedule() {
        return descriptionSchedule;
    }

    public void setDescriptionSchedule(String descriptionSchedule) {
        this.descriptionSchedule = descriptionSchedule;
    }

    public int getImgAttachedSchedule() {
        return imgAttachedSchedule;
    }

    public void setImgAttachedSchedule(int imgAttachedSchedule) {
        this.imgAttachedSchedule = imgAttachedSchedule;
    }

    public String getTimeSend() {
        return timeSend;
    }

    public void setTimeSend(String timeSend) {
        this.timeSend = timeSend;
    }

    public String getTextAttachedSchedule() {
        return textAttachedSchedule;
    }

    public void setTextAttachedSchedule(String textAttachedSchedule) {
        this.textAttachedSchedule = textAttachedSchedule;
    }

    public int getImgStarSchedule() {
        return imgStarSchedule;
    }

    public void setImgStarSchedule(int imgStarSchedule) {
        this.imgStarSchedule = imgStarSchedule;
    }
}
