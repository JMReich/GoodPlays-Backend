package com.jacobreich.GoodPlays.GameRetrieval.Steam;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<SteamGamesJson, Long> {

    List<SteamGamesJson> findByNameContainingIgnoreCase(String search);
}
