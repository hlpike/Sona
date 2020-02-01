package com.teammh.sona.model;

import java.util.ArrayList;

public class User {
    private String username;
    private String email;
    private String picURL;
    private ArrayList<Score> scores;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        scores = new ArrayList<Score>();
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPicURL() {
        return picURL;
    }

    public void addScore(int n) {
        scores.add(new Score(n));
    }

    public Score getScore(int n) {
        return scores.get(n);
    }
}
