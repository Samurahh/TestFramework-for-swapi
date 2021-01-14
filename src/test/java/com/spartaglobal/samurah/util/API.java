package com.spartaglobal.samurah.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spartaglobal.samurah.dtos.RootDTO;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

public class API {

    public static final String BASE_URL = "https://swapi.dev/api/";

    public static final API client = new API();

    private static RootDTO root;

    public final LinkedList<Map.Entry<Connection, JsonObject>> requests;

    private API(){
        this.requests = new LinkedList<>();
    }

    public RootDTO root() throws Exception {
        if(root == null){
            root = RootDTO.createFrom(this);
        }
        return root;
    }

    public JsonObject requestRoot() throws Exception {
        return request(BASE_URL);
    }

    public JsonObject request(String url) throws IOException, InterruptedException {
        return request(URL.decode(url));
    }

    public JsonObject request(String path, String query) throws IOException, InterruptedException {
        return request(URL.build(BASE_URL,path,query));
    }

    public JsonObject request(URL url) throws IOException, InterruptedException {
        Connection currentConnection = Connection.connect(url);
        if(currentConnection.statusCode() != 200){
            throw new RuntimeException("Failed: HTTP error code: "+ currentConnection.statusCode());
        }
        JsonObject jsonObject = deserialize(currentConnection.getResponse().body());
        requests.add(Map.entry(currentConnection, jsonObject));
        return jsonObject;
    }

    public JsonObject deserialize(String json){
        return new Gson().fromJson(json, JsonObject.class);
    }

    public Map.Entry<Connection, JsonObject> getLastRequest(){
        return requests.getLast();
    }
}
