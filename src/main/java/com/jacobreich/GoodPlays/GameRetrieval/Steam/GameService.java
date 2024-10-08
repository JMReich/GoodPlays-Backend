package com.jacobreich.GoodPlays.GameRetrieval.Steam;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final BlacklistRepository blacklistRepository;
    private final RestTemplate restTemplate;
    // List for games to be batch saved



    public GameService(GameRepository gameRepository, BlacklistRepository blacklistRepository, RestTemplate restTemplate) {
        this.gameRepository = gameRepository;
        this.blacklistRepository = blacklistRepository;
        this.restTemplate = restTemplate;

    }

    @Transactional
    public List<SteamApps> fetchAndSaveGames() {
        // Get all apps from the Steam API
        String appsUrl = "http://api.steampowered.com/ISteamApps/GetAppList/v0002/?format=json";
        ResponseEntity<SteamGamesList> responseEntity = restTemplate.getForEntity(appsUrl, SteamGamesList.class);
        SteamGamesList steanApps = responseEntity.getBody();



        if (steanApps != null && steanApps.getAppList() != null) {
            // Store apps in a list to access the app id
            List<SteamApps> appList = steanApps.getAppList().getApps();

            System.out.println("Total Apps: " + appList.size());
            int x = 0;
            int max_retry = 50;
            int retry = 0;
            boolean retry_flag = false;

            // Check if the app is a game using for each loop
            for (SteamApps app : appList) {

                // Needs to be set up here for the db checking
                retry_flag = false;
                retry = 0;

                while (retry < max_retry && !retry_flag) {
                    if (x == 1000) {
                        return null;
                    }
                    // Check if the app is in the blacklist table in the database
                    if (blacklistRepository.existsByappid(app.getAppid())) {
                        // Skip the app if it is in the blacklist
                        System.out.println("Skipping app: " + app.getAppid());
                        retry_flag = true;
                        continue;

                        // Temporary check to speed up testing
                    } else if (gameRepository.existsByappid(app.getAppid())) {
                        // Skip the app if it is already in the database
                        System.out.println("Skipping saved app: " + app.getAppid());
                        retry_flag = true;
                        continue;

                    } else {
                        // wait for 1.5 seconds before making another request
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


                    // Send a GET request to the Steam API to get a json for each app
                    String gameDetailsUrl = "https://store.steampowered.com/api/appdetails?appids=" + app.getAppid();

                    try {
                        ResponseEntity<SteamAppDetails> steamGameJsonResponseEntity = restTemplate.getForEntity(gameDetailsUrl, SteamAppDetails.class);
                        SteamAppDetails steamGame = steamGameJsonResponseEntity.getBody();


                        if (steamGame == null || !steamGame.getSuccess() || !Objects.equals(steamGame.getType(), "game")) {
                            // Add the app to the blacklist
                            System.out.println("Blacklisting app: " + app.getAppid());

                            BlackListEntry blackListEntry = new BlackListEntry();
                            blackListEntry.setAppid(app.getAppid());

                            blacklistRepository.save(blackListEntry);

                            continue;
                        }

                        // Save the game to the database
                        System.out.println("Saving app: " + app.getAppid());
                        x++;
                        gameRepository.save(steamGame);
                        retry_flag = true;
                    } catch (Exception e) {
                        System.out.println("Error: " + e);
                        retry++;
                        if(retry == max_retry) {
                            System.err.println("Max retries reached for: " + app.getAppid());
                        }

                    }


                }
            }


            // Save all blacklisted games in the list
            //blacklistRepository.saveAll(gamesToBlacklist);
        }

        return null;
    }



    public List<SteamAppDetails> getAllGames() {
        return gameRepository.findAll();
    }

    public List<SteamAppDetails> searchGames(String search) {
        // Fuzzy search with partial string matching (like %search% in SQL)
        return gameRepository.findByNameContainingIgnoreCase(search);
    }
}
