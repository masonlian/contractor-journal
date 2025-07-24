package com.masonlian.thejournal.dto;

import com.masonlian.thejournal.constant.Level;

import java.util.List;

//當面對到新的情景但現有的資源或行為應付有限，也許正是時候自定義新的行為模式與尋求新的資源。
public class LoginResponse {

    private String token;
    private Integer userId;
    private String userName;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
