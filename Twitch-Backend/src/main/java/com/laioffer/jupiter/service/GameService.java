package com.laioffer.jupiter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.jupiter.entity.db.Item;
import com.laioffer.jupiter.entity.db.ItemType;
import com.laioffer.jupiter.entity.response.Game;
import org.springframework.stereotype.Service;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Service
public class GameService {

    private static final String TOKEN = "Bearer 0hdu3hb9azppbnlzqbzftjoxtniu6l";
    private static final String CLIENT_ID = "kdgeg8bn3le2t4a0w1ufjl6e87k0t5";
    private static final String TOP_GAME_URL = "https://api.twitch.tv/helix/games/top?first=%s";
    private static final String GAME_SEARCH_URL_TEMPLATE = "https://api.twitch.tv/helix/games?name=%s";
    private static final int DEFAULT_GAME_LIMIT = 20;

    private static final String STREAM_SEARCH_URL_TEMPLATE = "https://api.twitch.tv/helix/streams?game_id=%s&first=%s";
    private static final String VIDEO_SEARCH_URL_TEMPLATE = "https://api.twitch.tv/helix/videos?game_id=%s&first=%s";
    private static final String CLIP_SEARCH_URL_TEMPLATE = "https://api.twitch.tv/helix/clips?game_id=%s&first=%s";
    private static final String TWITCH_BASE_URL = "https://www.twitch.tv/";
    private static final int DEFAULT_SEARCH_LIMIT = 20;


    // Build the request URL which will be used when calling Twitch APIs, e.g. https://api.twitch.tv/helix/games/top when trying to get top games.
    private String buildGameURL(String url, String gameName, int limit) {
        if (gameName.equals("")) {
            return String.format(url, limit);
        } else {
            try {
                // Encode special characters in URL, e.g. Rick Sun -> Rick%20Sun
                gameName = URLEncoder.encode(gameName, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return String.format(url, gameName);
        }
    }

    private String buildSearchURL(String url, String gameId, int limit) {
        try {
            gameId = URLEncoder.encode(gameId, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return String.format(url, gameId, limit);
    }

    // Send HTTP request to Twitch Backend based on the given URL, and returns the body of the HTTP response returned from Twitch backend.
    private String searchTwitch(String url) throws TwitchException {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        // Define the response handler to parse and return HTTP response body returned from Twitch
        ResponseHandler<String> responseHandler = response -> {
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode != 200) {
                System.out.println("Response status: " + response.getStatusLine().getReasonPhrase());
                throw new TwitchException("Failed to get result from Twitch API");
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                throw new TwitchException("Failed to get result from Twitch API");
            }
            JSONObject obj = new JSONObject(EntityUtils.toString(entity));
            return obj.getJSONArray("data").toString();
        };

        try {
            // Define the HTTP request, TOKEN and CLIENT_ID are used for user authentication on Twitch backend
            HttpGet request = new HttpGet(url);
            request.setHeader("Authorization", TOKEN);
            request.setHeader("Client-Id", CLIENT_ID);
            return httpclient.execute(request, responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
            throw new TwitchException("Failed to get result from Twitch API");
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Convert JSON format data returned from Twitch to an Arraylist of Game objects
    private List<Game> getGameList(String data){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return Arrays.asList(mapper.readValue(data, Game[].class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to parse game data from Twitch API");
        }
    }

    // Integrate search() and getGameList() together, returns the top x popular games from Twitch.
    public List<Game> topGames(int limit){
        if (limit <= 0) {
            limit = DEFAULT_GAME_LIMIT;
        }
        return getGameList(searchTwitch(buildGameURL(TOP_GAME_URL, "", limit)));
    }

    // Integrate search() and getGameList() together, returns the dedicated game based on the game name.
    public Game searchGame(String gameName){
        List<Game> gameList = getGameList(searchTwitch(buildGameURL(GAME_SEARCH_URL_TEMPLATE, gameName, 0)));
        if (gameList.size() != 0) {
            return gameList.get(0);
        }
        return null;
    }

    private List<Item> getItemList(String data) throws TwitchException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return Arrays.asList(mapper.readValue(data, Item[].class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new TwitchException("Failed to parse item data from Twitch API");
        }
    }

    // Returns the top x streams based on game ID.
    private List<Item> searchStreams(String gameId, int limit) throws TwitchException {
        List<Item> streams = getItemList(searchTwitch(buildSearchURL(STREAM_SEARCH_URL_TEMPLATE, gameId, limit)));
        for (Item item : streams) {
            item.setType(ItemType.STREAM);
            item.setUrl(TWITCH_BASE_URL + item.getBroadcasterName());
        }
        return streams;
    }

    // Returns the top x clips based on game ID.
    private List<Item> searchClips(String gameId, int limit) throws TwitchException {
        List<Item> clips = getItemList(searchTwitch(buildSearchURL(CLIP_SEARCH_URL_TEMPLATE, gameId, limit)));
        for (Item item : clips) {
            item.setType(ItemType.CLIP);
        }
        return clips;
    }

    // Returns the top x videos based on game ID.
    private List<Item> searchVideos(String gameId, int limit) throws TwitchException {
        List<Item> videos = getItemList(searchTwitch(buildSearchURL(VIDEO_SEARCH_URL_TEMPLATE, gameId, limit)));
        for (Item item : videos) {
            item.setType(ItemType.VIDEO);
        }
        return videos;
    }

    public List<Item> searchByType(String gameId, ItemType type, int limit) throws TwitchException {
        List<Item> items = Collections.emptyList();

        switch (type) {
            case STREAM:
                items = searchStreams(gameId, limit);
                break;
            case VIDEO:
                items = searchVideos(gameId, limit);
                break;
            case CLIP:
                items = searchClips(gameId, limit);
                break;
        }

        // Update gameId for all items. GameId is used by recommendation function
        for (Item item : items) {
            item.setGameId(gameId);
        }
        return items;
    }

    public Map<String, List<Item>> searchItems(String gameId) throws TwitchException {
        Map<String, List<Item>> itemMap = new HashMap<>();
        for (ItemType type : ItemType.values()) {
            itemMap.put(type.toString(), searchByType(gameId, type, DEFAULT_SEARCH_LIMIT));
        }
        return itemMap;
    }
}



/*
package com.laioffer.jupiter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.jupiter.entity.db.Item;
import com.laioffer.jupiter.entity.db.ItemType;
import com.laioffer.jupiter.entity.response.Game;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Service
public class GameService {
    private static final String TOKEN = "Bearer difv0ghfwwdwq1egd49jwlbb65pwfb";
    private static final String CLIENT_ID = "j25o7cd3hiwzjqcqznu9e73mht7zng";
    private static final String TOP_GAME_URL = "https://api.twitch.tv/helix/games/top?first=%s";
    private static final String GAME_SEARCH_URL_TEMPLATE = "https://api.twitch.tv/helix/games?name=%s";
    private static final int DEFAULT_GAME_LIMIT = 20;

    private static final String STREAM_SEARCH_URL_TEMPLATE = "https://api.twitch.tv/helix/streams?game_id=%s&first=%s";
    private static final String VIDEO_SEARCH_URL_TEMPLATE = "https://api.twitch.tv/helix/videos?game_id=%s&first=%s";
    private static final String CLIP_SEARCH_URL_TEMPLATE = "https://api.twitch.tv/helix/clips?game_id=%s&first=%s";
    // this is for setting stream url, a combination, because there is no "url" response for stream
    private static final String TWITCH_BASE_URL = "https://www.twitch.tv/";
    private static final int DEFAULT_SEARCH_LIMIT = 20;



    */
/* This method will help generate the correct URL when we call Twitch Game API.
    This is for game panel * *//*

    private String buildGameURL(String url, String gameName, int limit){
        if (gameName.equals("")){
            return String.format(url, limit);
        }else{
            try{
                gameName = URLEncoder.encode(gameName, "UTF-8");
            }catch (UnsupportedEncodingException ex){
                ex.printStackTrace();
            }
            return String.format(url, gameName);
        }
    }




    */
/*Invoke this method to generate an url and send to twitch. This is for search panel
    * // Similar to buildGameURL, build Search URL that will be used when calling Twitch API.
    * e.g. https://api.twitch.tv/helix/clips?game_id=12924.
    * *//*

    private String buildSearchURL(String url, String gameId, int limit) {
        try{
            gameId = URLEncoder.encode(gameId, "UTF-8");
        }catch(UnsupportedEncodingException ex){
            ex.printStackTrace();;
        }
        return String.format(url, gameId, limit);
    }



    */
/* Send HTTP request to Twitch Backend based on the given URL,
    and returns the body of the HTTP response returned from Twitch backend. *//*

    private String searchTwitch(String url) throws TwitchException{
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // Define the response handler to parse and return HTTP response body returned from Twitch
        ResponseHandler<String> responseHandler = response -> {
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode != 200){
                System.out.println("Response status: " + response.getStatusLine().getReasonPhrase());
                throw new TwitchException("Failed to get result from Twitch API");
            }

            HttpEntity entity = response.getEntity();
            if (entity == null){
                throw new TwitchException("Failed to get result from Twitch API");
            }

            JSONObject object = new JSONObject(EntityUtils.toString(entity));
            return object.getJSONArray("data").toString();
        };

        try{
            // Define the HTTP request, TOKEN and CLIENT_ID are used for user authentication on Twitch backend
            HttpGet request = new HttpGet(url);
            request.setHeader("Authorization", TOKEN);
            request.setHeader("Client-Id", CLIENT_ID);
            return httpClient.execute(request, responseHandler);
        }catch (IOException e){
            e.printStackTrace();
            throw new TwitchException("Failed to get result from Twitch API.");
        }finally {
            try{
                httpClient.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    */
/*Finally, add the getGameList method to convert Twitch returned data to a list of Game objects.
    Then, provide two public methods topGames and searchGame to return the top game list, or a dedicated game.*//*

//    Convert JSON format data returned from Twitch to an Arraylist of Game objects
    private List<Game> getGameList(String data) {
        ObjectMapper mapper = new ObjectMapper();
        try{
            return Arrays.asList(mapper.readValue(data, Game[].class));
        }catch (JsonProcessingException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to parse data from Twitch API.");
        }
    }

    */
/* Integrate search() and getGameList() together, returns the top x popular games from Twitch.*//*

    public List<Game> topGames(int limit){
        if (limit <= 0){
            limit = DEFAULT_GAME_LIMIT;
        }
        String url = buildGameURL(TOP_GAME_URL, "", limit);
        String data = searchTwitch(url);

        return getGameList(data);
    }

    public Game searchGame(String gameName){
        String url = buildGameURL(GAME_SEARCH_URL_TEMPLATE, gameName, 0);
        String data = searchTwitch(url);
        List<Game> gameList = getGameList(data);

        if (gameList.size() != 0){
            return gameList.get(0);
        }
        return null;
    }

    */
/* Helper function to convert the response Json object array to Item list*//*

    */
/*Similar to getGameList, convert the json data returned from Twitch to a list of Item objects.*//*

    private List<Item> getItemList(String data){
        ObjectMapper mapper = new ObjectMapper();
        try{
            return Arrays.asList(mapper.readValue(data, Item[].class));
        }catch(JsonProcessingException ex){
            ex.printStackTrace();
            throw new RuntimeException("Failed to parse item data from Wtitch API");
        }
    }

    */
/*Returns the top x streams based on game ID.*//*

    private List<Item> searchStreams(String gameId, int limit){
        String url = buildSearchURL(STREAM_SEARCH_URL_TEMPLATE, gameId, limit);
        String data = searchTwitch(url);
        List<Item> streams = getItemList(data);

        for (Item item : streams){
            item.setUrl(TWITCH_BASE_URL + item.getBroadcasterName());
            item.setType(ItemType.STREAM);
        }
        return streams;
    }

    */
/* Returns the top x clips based on game ID.**//*

    private List<Item> searchClips(String gameId, int limit){
        String url = buildSearchURL(CLIP_SEARCH_URL_TEMPLATE, gameId, limit);
        String data = searchTwitch(url);
        List<Item> clips = getItemList(data);

        for (Item item : clips){
            item.setType(ItemType.CLIP);
        }

        return clips;
    }

    */
/* Returns the top x videos based on game ID.*//*

    private List<Item> searchVideos(String gameId, int limit){
        String url = buildSearchURL(VIDEO_SEARCH_URL_TEMPLATE, gameId, limit);
        String data = searchTwitch(url);
        List<Item> videos = getItemList(data);

        for(Item item : videos){
            item.setType(ItemType.VIDEO);
        }

        return videos;
    }

    public List<Item> searchByType(String gameId, ItemType type, int limit){
        List<Item> items = new ArrayList<>();
        switch (type){
            case STREAM:
                items = searchStreams(gameId, limit);
                break;
            case VIDEO:
                items = searchVideos(gameId, limit);
                break;
            case CLIP:
                items = searchClips(gameId, limit);
                break;
        }
        // Update gameId for all items. GameId is used by recommendation function
        for (Item item : items) {
            item.setGameId(gameId);
        }

        return items;
    }

    */
/*In the map, key is the item type*//*

    public Map<String, List<Item>> searchItems(String gameId){
        Map<String, List<Item>> itemMap = new HashMap<>();
        for(ItemType type : ItemType.values()){
            itemMap.put(type.name(), searchByType(gameId, type, DEFAULT_GAME_LIMIT));
        }
        return itemMap;
    }
}*/
