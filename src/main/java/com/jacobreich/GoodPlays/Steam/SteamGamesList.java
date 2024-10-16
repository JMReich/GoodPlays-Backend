package com.jacobreich.GoodPlays.Steam;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class SteamGamesList {



    @JsonProperty("applist")
    private AppList appList;

    public AppList getAppList() {

        return appList;
    }

    public void setAppList(AppList appList) {
        this.appList = appList;
    }

    public static class AppList {
        private List<SteamApps> apps;

        public List<SteamApps> getApps() {
            return apps;
        }

        public void setApps(List<SteamApps> apps) {
            this.apps = apps;
        }
    }
}


