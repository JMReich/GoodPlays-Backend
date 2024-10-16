package com.jacobreich.GoodPlays.Steam;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/*
This class is used to deserialize the JSON response from the Steam API.
This is necessary because the JSON response appid key is different every time.
 */

public class SteamAppDetailsDeserializer extends JsonDeserializer<SteamAppDetails> {

    @Override
    public SteamAppDetails deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);
        SteamAppDetails responseGame = new SteamAppDetails();

        // Assign the appid key to the response object
        String appid = rootNode.fieldNames().next(); // cast to int
        responseGame.setAppid(Integer.parseInt(appid));

        JsonNode appNode = rootNode.get(appid);
        JsonNode dataNode = appNode.get("data");


        responseGame.setSuccess(appNode.get("success").asBoolean());
        if (responseGame.getSuccess()) {
            responseGame.setName(dataNode.has("name") ? dataNode.get("name").asText() : null);
            responseGame.setRequiredAge(dataNode.has("required_age") ? Integer.parseInt(dataNode.get("required_age").asText()) : null);
            responseGame.setHeaderImage(dataNode.has("header_image") ? dataNode.get("header_image").asText() : null);
            responseGame.setMetacritic(!dataNode.has("metacritic") ? null : dataNode.get("metacritic").has("score") ? dataNode.get("metacritic").get("score").asText() : null);
            responseGame.setRecommendations(dataNode.has("recommendations") ? dataNode.get("recommendations").get("total").asText() : null);
            responseGame.setType(dataNode.has("type") ? dataNode.get("type").asText() : null);
            //responseGame.setSteamAppid(dataNode.has("steam_appid") ? dataNode.get("steam_appid").asInt() : 0);
            //responseGame.setIsFree(dataNode.has("is_free") ? dataNode.get("is_free").asBoolean() : false);
            //responseGame.setControllerSupport(dataNode.has("controller_support") ? dataNode.get("controller_support").asText() : null);
            //responseGame.setDlc(dataNode.has("dlc") ? StreamSupport.stream(dataNode.get("dlc").spliterator(), false).map(JsonNode::asInt).collect(Collectors.toList()) : new ArrayList<>());
            //responseGame.setDetailedDescription(dataNode.has("detailed_description") ? dataNode.get("detailed_description").asText() : null);
            //responseGame.setAboutTheGame(dataNode.has("about_the_game") ? dataNode.get("about_the_game").asText() : null);
            //responseGame.setShortDescription(dataNode.has("short_description") ? dataNode.get("short_description").asText() : null);
            //responseGame.setSupportedLanguages(dataNode.has("supported_languages") ? dataNode.get("supported_languages").asText() : null);
            //responseGame.setCapsuleImage(dataNode.has("capsule") ? dataNode.get("capsule").asText() : null);
            //responseGame.setCapsuleImageV5(dataNode.has("capsule_image_v5") ? dataNode.get("capsule_image_v5").asText() : null);
            //responseGame.setWebsite(dataNode.has("website") ? dataNode.get("website").asText() : null);
            //responseGame.setPcRequirements(dataNode.has("pc_requirements") ? dataNode.get("pc_requirements").asText() : null);
            //responseGame.setMacRequirements(dataNode.has("mac_requirements") ? dataNode.get("mac_requirements").asText() : null);
            //responseGame.setLinuxRequirements(dataNode.has("linux_requirements") ? dataNode.get("linux_requirements").asText() : null);
            //responseGame.setLegalNotice(dataNode.has("legal_notice") ? dataNode.get("legal_notice").asText() : null);
            //responseGame.setDevelopers(dataNode.has("developers") ? StreamSupport.stream(dataNode.get("developers").spliterator(), false).map(JsonNode::asText).collect(Collectors.toList()) : new ArrayList<>());
            //responseGame.setPublishers(dataNode.has("publishers") ? StreamSupport.stream(dataNode.get("publishers").spliterator(), false).map(JsonNode::asText).collect(Collectors.toList()) : new ArrayList<>());
            //responseGame.setPriceOverview(dataNode.has("price_overview") ? StreamSupport.stream(dataNode.get("price_overview").spliterator(), false).map(JsonNode::asText).collect(Collectors.toList()) : new ArrayList<>());
            //responseGame.setPackages(dataNode.has("packages") ? StreamSupport.stream(dataNode.get("packages").spliterator(), false).map(JsonNode::asInt).collect(Collectors.toList()) : new ArrayList<>());
            //responseGame.setPackageGroups(dataNode.has("package_groups") ? StreamSupport.stream(dataNode.get("package_groups").spliterator(), false).map(JsonNode::asText).collect(Collectors.toList()) : new ArrayList<>());
            //responseGame.setPlatforms(dataNode.has("platforms") ? StreamSupport.stream(dataNode.get("platforms").spliterator(), false).map(JsonNode::asText).collect(Collectors.toList()) : new ArrayList<>());
            //responseGame.setCategories(dataNode.has("categories") ? StreamSupport.stream(dataNode.get("categories").spliterator(), false).map(JsonNode::asText).collect(Collectors.toList()) : new ArrayList<>());
            //responseGame.setGenres(dataNode.has("genres") ? StreamSupport.stream(dataNode.get("genres").spliterator(), false).map(JsonNode::asText).collect(Collectors.toList()) : new ArrayList<>());
            //responseGame.setScreenshots(dataNode.has("screenshots") ? StreamSupport.stream(dataNode.get("screenshots").spliterator(), false).map(JsonNode::asText).collect(Collectors.toList()) : new ArrayList<>());
            //responseGame.setMovies(dataNode.has("movies") ? StreamSupport.stream(dataNode.get("movies").spliterator(), false).map(JsonNode::asText).collect(Collectors.toList()) : new ArrayList<>());
            //responseGame.setAchievements(dataNode.has("achievements") ? StreamSupport.stream(dataNode.get("achievements").spliterator(), false).map(JsonNode::asText).collect(Collectors.toList()) : new ArrayList<>());
            //responseGame.setReleaseDate(dataNode.has("release_date") ? StreamSupport.stream(dataNode.get("release_date").spliterator(), false).map(JsonNode::asText).collect(Collectors.toList()) : new ArrayList<>());
            //responseGame.setSupportInfo(dataNode.has("support_info") ? StreamSupport.stream(dataNode.get("support_info").spliterator(), false).map(JsonNode::asText).collect(Collectors.toList()) : new ArrayList<>());
            //responseGame.setBackground(dataNode.has("background") ? dataNode.get("background").asText() : null);
            //responseGame.setBackgroundRaw(dataNode.has("background_raw") ? dataNode.get("background_raw").asText() : null);
            //responseGame.setContentDescriptors(dataNode.has("content_descriptors") ? StreamSupport.stream(dataNode.get("content_descriptors").spliterator(), false).map(JsonNode::asText).collect(Collectors.toList()) : new ArrayList<>());
            //responseGame.setRatings(dataNode.has("ratings") ? StreamSupport.stream(dataNode.get("ratings").spliterator(), false).map(JsonNode::asText).collect(Collectors.toList()) : new ArrayList<>());
        }

        return responseGame;
    }
}