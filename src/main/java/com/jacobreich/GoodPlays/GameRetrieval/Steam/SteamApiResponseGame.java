package com.jacobreich.GoodPlays.GameRetrieval.Steam;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class SteamApiResponseGame {

    private Map<String, SteamApiResponseGame> appDetails;

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("data")
    private List data;

    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Map<String, SteamApiResponseGame> getAppDetails() {
        return appDetails;
    }

}
