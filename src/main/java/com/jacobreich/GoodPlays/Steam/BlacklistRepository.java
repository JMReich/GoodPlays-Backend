package com.jacobreich.GoodPlays.Steam;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistRepository extends JpaRepository<BlackListEntry, Long> {


    boolean existsByappid(int appId);
}
