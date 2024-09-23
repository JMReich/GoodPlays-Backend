package com.jacobreich.GoodPlays.GameRetrieval.Steam;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class SteamApiResponseGames {



    @JsonProperty("applist")
    private AppList appList;

    public AppList getAppList() {

        return appList;
    }

    public void setAppList(AppList appList) {
        this.appList = appList;
    }

    public static class AppList {
        private List<SteamGamesJson> apps;

        public List<SteamGamesJson> getApps() {
            return apps;
        }

        public void setApps(List<SteamGamesJson> apps) {
            this.apps = apps;
        }
    }
}


