package com.jacobreich.GoodPlays;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jacobreich.GoodPlays.IGDB.IgdbGameService;
import com.jacobreich.GoodPlays.Nintendo.NintendoGameService;
import com.jacobreich.GoodPlays.PlayStation.PlayStationGameService;
import com.jacobreich.GoodPlays.Steam.SteamGameService;
import com.jacobreich.GoodPlays.Steam.SteamAppDetails;
import com.jacobreich.GoodPlays.Steam.SteamApps;
import com.jacobreich.GoodPlays.Xbox.XboxGameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameRetrievalController {

    private final SteamGameService steamGameService;
    private final IgdbGameService igdbGameService;
    private final NintendoGameService nintendoGameService;
    private final PlayStationGameService playStationGameService;
    private final XboxGameService xboxGameService;

    public GameRetrievalController(SteamGameService steamGameService, IgdbGameService igdbGameService, NintendoGameService nintendoGameService, PlayStationGameService playStationGameService, XboxGameService xboxGameService) {
        this.steamGameService = steamGameService;
        this.igdbGameService = igdbGameService;
        this.nintendoGameService = nintendoGameService;
        this.playStationGameService = playStationGameService;
        this.xboxGameService = xboxGameService;
    }

//    @GetMapping("/fetchGames")
//    public void fetchGames() {
//        steamGameService.fetchAndSaveGames();
//    }


    @GetMapping("/fetchGames")
    public void fetchGames() throws JsonProcessingException {
        List<SteamApps> games = steamGameService.fetchAndSaveGames();
        // System.out.println("Games fetched: " + games.toString());
        //games.forEach(game -> System.out.println("Game ID: " + game.getAppId() + ", Game Name: " + game.getName()));

    }


    // Fetches all games from the database
    @GetMapping("/getGames")
    public List<SteamAppDetails> getGames() {
        return steamGameService.getAllGames();
    }

    // Fetches all matching games with a fuzzy search
    @GetMapping("/searchGames")
    public List<SteamAppDetails> searchGames(@RequestParam String search, @RequestParam int platform) {
        // Switch case for database
        switch(platform) {
            case 1: // Steam
                return steamGameService.searchGames(search);
            case 2: // IGDB
                return steamGameService.searchGames(search);
            case 3: // PlayStation
                return steamGameService.searchGames(search);
            case 4: // Xbox
                return steamGameService.searchGames(search);
            case 5: // Nintendo
                return steamGameService.searchGames(search);
            default: // Steam
                return steamGameService.searchGames(search);
        }
    }

    // Fetches all unique game types
//    @GetMapping("/getUniqueGameTypes")
//    public List<String> getUniqueGameTypes() {
//        return steamGameService.getUniqueGameTypes();
//    }
}
