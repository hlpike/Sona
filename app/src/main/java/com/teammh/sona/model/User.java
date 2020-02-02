package com.teammh.sona.model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String username;
    private String email;
    private String picURL;
    private ArrayList<Score> scores;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        picURL = "";
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

    public void addScore(Score s) {
        scores.add(s);
    }

    public Score getScore(int n) {
        if (scores != null && scores.size() > n) {
            return scores.get(n);
        } else {
            return null;
        }
    }

}
