package com.jacobreich.GoodPlays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.jacobreich.GoodPlays.GameRetrieval.Steam.SteamAppDetails;
import com.jacobreich.GoodPlays.GameRetrieval.Steam.SteamAppDetailsDeserializer;
import com.jacobreich.GoodPlays.GameRetrieval.Steam.SteamApps;
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
        module.addDeserializer(SteamAppDetails.class, new SteamAppDetailsDeserializer());
        mapper.registerModule(module);
        return mapper;
    }
}