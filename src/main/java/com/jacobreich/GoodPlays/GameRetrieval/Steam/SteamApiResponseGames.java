package com.jacobreich.GoodPlays.GameRetrieval.Steam;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

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
        private List<SteamGamesTable> apps;

        public List<SteamGamesTable> getApps() {
            return apps;
        }

        public void setApps(List<SteamGamesTable> apps) {
            this.apps = apps;
        }
    }
}


