package com.jacobreich.GoodPlays.Steam;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;



public class SteamApps {
    @Id
    private int appId;

    // Getters and setters
    public int getAppid() {
        return appId;
    }

    public void setAppid(int appid) {
        this.appId = appid;
    }
}
