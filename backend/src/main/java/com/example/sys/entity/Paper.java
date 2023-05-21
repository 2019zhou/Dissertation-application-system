package com.example.sys.entity;

/**
 * @Author TangZT
 */
public class Paper {

    private String userID;

    private String username;

    private String title;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "userID='" + userID + '\'' +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
