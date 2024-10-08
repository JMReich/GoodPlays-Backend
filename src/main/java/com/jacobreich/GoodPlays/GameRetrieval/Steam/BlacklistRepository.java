package com.jacobreich.GoodPlays.GameRetrieval.Steam;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlacklistRepository extends JpaRepository<BlackListEntry, Long> {


    boolean existsByappid(int appId);
}
