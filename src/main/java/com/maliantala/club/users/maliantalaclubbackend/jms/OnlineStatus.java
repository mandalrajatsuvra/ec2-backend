package com.maliantala.club.users.maliantalaclubbackend.jms;

import java.util.HashMap;
import java.util.Map;

public class OnlineStatus {
    private Map<Integer , Integer> onlineStatus = new HashMap<>();
    OnlineStatus(){
    }
    public Map<Integer, Integer> getOnlineStatus() {
        return onlineStatus;
    }
    public void setOnlineStatus(Map<Integer, Integer> onlineStatus) {
        this.onlineStatus = onlineStatus;
    }
}
