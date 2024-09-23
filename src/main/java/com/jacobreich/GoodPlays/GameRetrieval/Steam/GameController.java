package com.jacobreich.GoodPlays.GameRetrieval.Steam;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

//    @GetMapping("/fetchGames")
//    public void fetchGames() {
//        gameService.fetchAndSaveGames();
//    }


    @GetMapping("/fetchGames")
    public void fetchGames() throws JsonProcessingException {
        List<SteamGamesJson> games = gameService.fetchAndSaveGames();
        // System.out.println("Games fetched: " + games.toString());
        //games.forEach(game -> System.out.println("Game ID: " + game.getAppId() + ", Game Name: " + game.getName()));

    }


    // Fetches all games from the database
    @GetMapping("/getGames")
    public List<SteamGamesJson> getGames() {
        return gameService.getAllGames();
    }

    // Fetches all matching games with a fuzzy search
    @GetMapping("/searchGames")
    public List<SteamGamesJson> searchGames(String search) {
        return gameService.searchGames(search);
    }

    // Fetches all unique game types
//    @GetMapping("/getUniqueGameTypes")
//    public List<String> getUniqueGameTypes() {
//        return gameService.getUniqueGameTypes();
//    }

}