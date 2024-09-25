package com.jacobreich.GoodPlays.GameRetrieval.Steam;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/*
This class is used to deserialize the JSON response from the Steam API.
This is necessary because the JSON response appid key is different every time.
 */

public class SteamApiResponseGameDeserializer extends JsonDeserializer<SteamApiResponseGame> {

    @Override
    public SteamApiResponseGame deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);
        SteamApiResponseGame responseGame = new SteamApiResponseGame();

        // Assuming there's only one appid key in the JSON
        String appid = rootNode.fieldNames().next();
        responseGame.setAppid(appid);

        System.out.println("appid: " + appid);

        JsonNode appNode = rootNode.get(appid);
        responseGame.setSuccess(appNode.get("success").asText());

        SteamApiResponseGame.AppDetails appDetails = new SteamApiResponseGame.AppDetails();
        JsonNode dataNode = appNode.get("data");

        if (appNode.get("success").asBoolean()) {
            appDetails.setType(dataNode.get("type").asText());
            System.out.println("type: " + dataNode.get("type").asText());
            //appDetails.setName(dataNode.get("name").asText());
            //appDetails.setSteamAppid(dataNode.get("steam_appid").asInt());
            //appDetails.setRequiredAge(dataNode.get("required_age").asText());
            //appDetails.setFree(dataNode.get("is_free").asBoolean());
            //appDetails.setControllerSupport(dataNode.get("controller_support").asText());

            // Handle DLC list
           // List<Integer> dlcList = new ArrayList<>();
//            for (JsonNode dlcNode : dataNode.get("dlc")) {
//                dlcList.add(dlcNode.asInt());
//            }
//            appDetails.setDlc(dlcList);

            responseGame.setAppDetails(appDetails);
        }

        return responseGame;
    }
}