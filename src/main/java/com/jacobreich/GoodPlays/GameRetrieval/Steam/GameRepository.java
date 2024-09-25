package com.jacobreich.GoodPlays.GameRetrieval.Steam;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<SteamGamesTable, Long> {

    List<SteamGamesTable> findByNameContainingIgnoreCase(String search);
}
