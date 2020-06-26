package com.example.rt.ModelClasses;

public class SliderItem {

    private String push_id ;
    private String userUid ;
    private String description ;
    private String imageUrl  ;
    private String timeStamp ;
    private String status ;

    public SliderItem() {
    }

    public SliderItem(String push_id, String userUid, String description, String imageUrl, String timeStamp, String status) {
        this.push_id = push_id;
        this.userUid = userUid;
        this.description = description;
        this.imageUrl = imageUrl;
        this.timeStamp = timeStamp;
        this.status = status;
    }

    public String getPush_id() {
        return push_id;
    }

    public void setPush_id(String push_id) {
        this.push_id = push_id;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
