package com.jacobreich.GoodPlays.GameRetrieval.Steam;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.util.List;

@JsonDeserialize(using = SteamApiResponseGameDeserializer.class)
public class SteamApiResponseGame {

    private String appid;
    private String success;
    private AppDetails appDetails;

    // Getters and setters

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public AppDetails getAppDetails() {
        return appDetails;
    }

    public void setAppDetails(AppDetails appDetails) {
        this.appDetails = appDetails;
    }

    public static class AppDetails {
        private String type;
        private String name;
        private int steamAppid;
        private String requiredAge;
        private boolean isFree;
        private String controllerSupport;
        private List<Integer> dlc;

        // Getters and setters

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSteamAppid() {
            return steamAppid;
        }

        public void setSteamAppid(int steamAppid) {
            this.steamAppid = steamAppid;
        }

        public String getRequiredAge() {
            return requiredAge;
        }

        public void setRequiredAge(String requiredAge) {
            this.requiredAge = requiredAge;
        }

        public boolean isFree() {
            return isFree;
        }

        public void setFree(boolean free) {
            isFree = free;
        }

        public String getControllerSupport() {
            return controllerSupport;
        }

        public void setControllerSupport(String controllerSupport) {
            this.controllerSupport = controllerSupport;
        }

        public List<Integer> getDlc() {
            return dlc;
        }

        public void setDlc(List<Integer> dlc) {
            this.dlc = dlc;
        }
    }
}