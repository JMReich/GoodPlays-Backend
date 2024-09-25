package com.jacobreich.GoodPlays.GameRetrieval.Steam;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public GameService(GameRepository gameRepository, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.gameRepository = gameRepository;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public List<SteamGamesTable> fetchAndSaveGames() {
        // Get all apps from the Steam API
        String appsUrl = "http://api.steampowered.com/ISteamApps/GetAppList/v0002/?format=json";
        ResponseEntity<SteamApiResponseGames> responseEntity = restTemplate.getForEntity(appsUrl, SteamApiResponseGames.class);
        SteamApiResponseGames steanApps = responseEntity.getBody();


        if (steanApps != null && steanApps.getAppList() != null) {
            // Store apps in a list to access the app id
            List<SteamGamesTable> appList = steanApps.getAppList().getApps();
            System.out.println("Total Apps: " + appList.size());
            Set<String> uniqueGameTypes = new HashSet<>();

            // Check if the app is a game using for each and store it in the list
            appList.forEach(app -> {
                try {
                    // Pause for 1.5 seconds
                    /*
                    TODO: Fix how long this takes to run

                    Currently, the program pauses for 1.5 seconds for each app in the list. This is not efficient and will take a long time to run. (~4 days)
                    This is due to a limitation with the api. The api only allows 200 requests per 5 minutes. This is a limitation of the api and cannot be changed.
                    To fix this, you can use a different api or bypass the limitation by using proxy servers (which is not recommended, and most likely against tos).
                    Not currently sure how to fix this, but it is a problem that needs to be addressed in the future as currently this
                    only needs to run once a month to update the database.
                    A potential soft "fix" for this would be to add a way to manually add games (If something important came out,
                     like elden ring, and it was not caught in the update), but this sucks because of the manual labor involved.
                    We could also bite the bullet and run this every week so the database is "Always" up to date, but this is not ideal.

                    ---- Current leading solution ----
                    Have a black list of app ID's to skip as they are not games.
                    This would make it so no DLC is stored in the database, but it would be a lot faster.
                    We could still show DLC in the under the game, and maybe in the search results....
                    It would still be nice to have a way for players to tag that they beat a dlc.... Maybe possible through the app record with the dlc id?
                    TODO: Find a way to blacklist non-game app ID's
                        - Save all apps to the database, appID, name, type 
                        - Blacklist non-games
                        - Find out how many game apps there are and how long it would take to save them all
                        - Find out how many dlc there are and how long it would take to save them all
                     */

                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Send a GET request to the Steam API to get a json for each app
                String gameDetailsUrl = "https://store.steampowered.com/api/appdetails?appids=" + app.getAppId();
                ResponseEntity<SteamApiResponseGame> steamGameJsonResponseEntity = restTemplate.getForEntity(gameDetailsUrl, SteamApiResponseGame.class);
                SteamApiResponseGame steamGame = steamGameJsonResponseEntity.getBody();

                if (steamGame != null && steamGame.getSuccess() != null && steamGame.getSuccess().equals("true")) {
                    // Print all unique game types
                    String gameType = steamGame.getAppDetails().getType();
                    uniqueGameTypes.add(gameType);


                    // Check if the app is a game
//                    if (steamGame.getAppDetails().getType().equals("game")) {
//                        // Save the game to the database
//                        gameRepository.save(app);
//                    }
                }
            });
            System.out.println("Unique Game Types: ");
            uniqueGameTypes.forEach(System.out::println);

        }






        // System.out.println("SteamGameJsonResponseEntity: " + steamGame.getAppDetails().getType());




//        try {
//            String json = objectMapper.writeValueAsString(apiResponse);
//            // System.out.println("Response: " + json);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        if (apiResponse != null && apiResponse.getAppList() != null) {
//            List<SteamGamesJson> gameList = apiResponse.getAppList().getApps();
//            // for each of the gameList, set the game's id to the appId
//            // gameList.forEach(game -> game.setAppId(game.getAppId()));
//            // for each print the game's id and name
//            // gameList.forEach(game -> System.out.println("Game ID: " + game.getAppId() + ", Game Name: " + game.getName()));
//
//            // Temp code to check all unique game types
//            // list to store unique game types
//            // List<String> uniqueGameTypes = new ArrayList<>();
//            // URL to get the game details: https://store.steampowered.com/api/appdetails?appids=
//
//
////            gameList.forEach(game -> {
////                try {
////                    // Pause for 15 seconds
////                    Thread.sleep(15000);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
////                String gameDetailsUrl = "https://store.steampowered.com/api/appdetails?appids=" + game.getAppId();
////                ResponseEntity<SteamGameJson> steamGameJsonResponseEntity = restTemplate.getForEntity(gameDetailsUrl, SteamGameJson.class);
////                SteamGameJson steamGameJson = steamGameJsonResponseEntity.getBody();
////                if (steamGameJsonResponseEntity != null && steamGameJson.getSuccess() != null) {
////                    String appType = steamGameJson.getType();
////                    if (!uniqueGameTypes.contains(appType)) {
////                        uniqueGameTypes.add(appType);
////                        System.out.println("Unique App Type: " + appType);
////                    }
////
////                }
////
////
////            });
//
//
//            //end temp code
//
//            // gameRepository.saveAll(gameList);
//            return gameList;
//        }
        return null;
    }

    public List<SteamGamesTable> getAllGames() {
        return gameRepository.findAll();
    }

    public List<SteamGamesTable> searchGames(String search) {
        // Fuzzy search with partial string matching (like %search% in SQL)
        return gameRepository.findByNameContainingIgnoreCase(search);
    }
}
