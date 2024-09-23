package com.jacobreich.GoodPlays.GameRetrieval.Steam;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SteamGameJson {

    @Id
    private int appid;
    private Boolean success;
    private String type;

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
