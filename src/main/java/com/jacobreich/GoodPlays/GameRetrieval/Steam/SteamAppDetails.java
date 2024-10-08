package com.jacobreich.GoodPlays.GameRetrieval.Steam;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;

import java.util.List;

@Entity
@JsonDeserialize(using = SteamAppDetailsDeserializer.class)
@Table(name = "steam_games")
public class SteamAppDetails {

    @Id
    @Column(name = "appid")
    @JsonProperty("appid")
    private int appid;
    private String name;
    @Transient
    private boolean success;
    private int requiredAge;
    private String headerImage;
    private String metacritic;
    private String recommendations;
    @Transient
    private String type;
    //private int steamAppid;
    //private boolean isFree;
    //private String controllerSupport;
    //private List<Integer> dlc;
    //private String detailedDescription;
    //private String aboutTheGame;
    //private String shortDescription;
    //private String supportedLanguages;
    //private String capsuleImage;
    //private String capsuleImageV5;
    //private String website;
    //private String pcRequirements; // Might need another class for this
    //private String macRequirements; // Might need another class for this
    //private String linuxRequirements; // Might need another class for this
    //private String legalNotice;
    //private List<String> developers;
    //private List<String> publishers;
    //private List<String> price_overview;
    //private List<Integer> packages;
    //private List<String> packageGroups;
    //private List<String> platforms;
    //private List<String> categories;
    //private List<String> genres;
    //private List<String> screenshots;
    //private List<String> movies;
    //private List<String> achievements;
    //private List<String> releaseDate;
    //private List<String> supportInfo;
    //private String background;
    //private String backgroundRaw;
    //private List<String> contentDescriptors;
    //private List<String> ratings;


    // Getters and setters
    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRequiredAge() {
        return requiredAge;
    }

    public void setRequiredAge(int requiredAge) {
        this.requiredAge = requiredAge;
    }

    public void setHeaderImage(String s) {
        this.headerImage = s;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public void setMetacritic(String s) {
        this.metacritic = s;
    }

    public String getMetacritic() {
        return metacritic;
    }

    public void setRecommendations(String s) {
        this.recommendations = s;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}