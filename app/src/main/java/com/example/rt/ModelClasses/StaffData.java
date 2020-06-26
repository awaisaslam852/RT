package com.example.rt.ModelClasses;

public class StaffData {

    //14

    private String pushId;
    private String created_date;
    private String full_name;
    private String email;
    private String phone;
    private String city;
    private String address;
    private String imgUrl;
    private String search_with;
    private String userType;
    private String isVerified;
    private String online_status;
    private String extra1;
    private String extra2;

    public StaffData() {
    }

    public StaffData(String pushId, String created_date, String full_name, String email, String phone, String city, String address, String imgUrl, String search_with, String userType, String isVerified, String online_status, String extra1, String extra2) {
        this.pushId = pushId;
        this.created_date = created_date;
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.address = address;
        this.imgUrl = imgUrl;
        this.search_with = search_with;
        this.userType = userType;
        this.isVerified = isVerified;
        this.online_status = online_status;
        this.extra1 = extra1;
        this.extra2 = extra2;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSearch_with() {
        return search_with;
    }

    public void setSearch_with(String search_with) {
        this.search_with = search_with;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(String isVerified) {
        this.isVerified = isVerified;
    }

    public String getOnline_status() {
        return online_status;
    }

    public void setOnline_status(String online_status) {
        this.online_status = online_status;
    }

    public String getExtra1() {
        return extra1;
    }

    public void setExtra1(String extra1) {
        this.extra1 = extra1;
    }

    public String getExtra2() {
        return extra2;
    }

    public void setExtra2(String extra2) {
        this.extra2 = extra2;
    }
}