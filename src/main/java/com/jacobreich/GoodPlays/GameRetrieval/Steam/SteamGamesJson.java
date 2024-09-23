package com.jacobreich.GoodPlays.GameRetrieval.Steam;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "steamGames")
public class SteamGamesJson {
    @Id
    @Column(name = "appid")
    @JsonProperty("appid")
    private int appId;

    @Column(name = "name")
    private String name;

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
