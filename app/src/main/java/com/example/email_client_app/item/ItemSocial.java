package com.example.email_client_app.item;

public class ItemSocial extends ItemEmail {
    private int imgMedia;
    private boolean errorSpam;

    public ItemSocial(String name, String date, int imgProfile, boolean starred, String subject, String description,
                      int imgMedia,
                      boolean errorSpam) {
        super(name, date, imgProfile, starred, subject, description);
        this.imgMedia = imgMedia;
        this.errorSpam = errorSpam;
    }

    public int getImgMedia() {
        return imgMedia;
    }

    public void setImgMedia(int imgMedia) {
        this.imgMedia = imgMedia;
    }

    public boolean isErrorSpam() {
        return errorSpam;
    }

    public void setErrorSpam(boolean errorSpam) {
        this.errorSpam = errorSpam;
    }

}
