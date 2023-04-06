package com.maliantala.club.users.maliantalaclubbackend.jms;

public class OnlineStatusPojo {
    private Integer onLine;
    private Integer userId;
    OnlineStatusPojo(){ }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getUserId() {
        return userId;
    }
    public Integer getOnLine() {
        return onLine;
    }
    public void setOnLine(Integer onLine) {
        this.onLine = onLine;
    }
}
