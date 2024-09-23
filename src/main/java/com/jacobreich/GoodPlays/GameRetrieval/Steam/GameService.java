package com.jacobreich.GoodPlays.GameRetrieval.Steam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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

    public List<SteamGamesJson> fetchAndSaveGames() {
        String gameDetailsUrl = "https://store.steampowered.com/api/appdetails?appids=311210";
        ResponseEntity<SteamApiResponseGame> steamGameJsonResponseEntity = restTemplate.getForEntity(gameDetailsUrl, SteamApiResponseGame.class);
        SteamApiResponseGame steamGameJson = steamGameJsonResponseEntity.getBody();



        System.out.println("SteamGameJsonResponseEntity: " + steamGameJson);
        System.out.println("SteamGameJson: " + steamGameJson.getAppDetails().get("311210").getName());
//        String url = "http://api.steampowered.com/ISteamApps/GetAppList/v0002/?format=json";
//        ResponseEntity<SteamApiResponse> responseEntity = restTemplate.getForEntity(url, SteamApiResponse.class);
//        SteamApiResponse apiResponse = responseEntity.getBody();
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

    public List<SteamGamesJson> getAllGames() {
        return gameRepository.findAll();
    }

    public List<SteamGamesJson> searchGames(String search) {
        // Fuzzy search with partial string matching (like %search% in SQL)
        return gameRepository.findByNameContainingIgnoreCase(search);
    }
}
