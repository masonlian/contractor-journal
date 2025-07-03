package com.masonlian.thejournal.model;

import com.masonlian.thejournal.constant.Level;

import java.security.PrivateKey;
import java.security.Timestamp;

public class User {
    private Integer userId;
    private String email;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Timestamp lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    private String password;
    private Level level;
    private Timestamp createdTime;
    private Timestamp lastModifiedTime;

    public void setCreatedTime(java.sql.Timestamp createdTime) {
    }

    public void setLastModifiedTime(java.sql.Timestamp lastModifiedTime) {

    }
}
