package com.jacobreich.GoodPlays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.jacobreich.GoodPlays.GameRetrieval.Steam.SteamApiResponseGame;
import com.jacobreich.GoodPlays.GameRetrieval.Steam.SteamApiResponseGameDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(SteamApiResponseGame.class, new SteamApiResponseGameDeserializer());
        mapper.registerModule(module);
        return mapper;
    }
}