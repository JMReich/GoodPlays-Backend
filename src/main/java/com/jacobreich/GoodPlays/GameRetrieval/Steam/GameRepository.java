package com.jacobreich.GoodPlays.GameRetrieval.Steam;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<SteamAppDetails, Long> {

    List<SteamAppDetails> findByNameContainingIgnoreCase(String search);

    boolean existsByappid(int appid);
}
