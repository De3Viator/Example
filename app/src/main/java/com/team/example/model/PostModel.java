package com.team.example.model;

public class PostModel {
    String postId;
    String postTitle;
    String postDescription;
    String postImage;
    String postTime;
    String uId;
    String puName;
    String pTimeStape;
    String puPicture;

    public String getPuPicture() {
        return puPicture;
    }

    public void setPuPicture(String puPicture) {
        this.puPicture = puPicture;
    }

    public PostModel(){

    }

    public String getpTimeStape() {
        return pTimeStape;
    }

    public void setpTimeStape(String pTimeStape) {
        this.pTimeStape = pTimeStape;
    }

    public PostModel(String postId, String postTitle, String postDescription, String postImage, String postTime, String uId, String puName, String pTimeStape, String puPicture) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.postImage = postImage;
        this.postTime = postTime;
        this.uId = uId;
        this.puName = puName;
        this.pTimeStape = pTimeStape;
        this.puPicture = puPicture;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getPuName() {
        return puName;
    }

    public void setPuName(String puName) {
        this.puName = puName;
    }
}
