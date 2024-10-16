package com.jacobreich.GoodPlays.Steam;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "black_list")
public class BlackListEntry {
    @Id
    @Column(name = "appid")
    @JsonProperty("appid")
    private int appid;


    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }
}
