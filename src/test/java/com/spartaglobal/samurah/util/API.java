package com.spartaglobal.samurah.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spartaglobal.samurah.dtos.RootDTO;
import com.spartaglobal.samurah.exceptions.RequestFailedException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

public class API {

    /**
     * The framework will use this base_url for any call,
     * appending the path and parameters required.
     */
    public static final String BASE_URL = "https://swapi.dev/api/";

    /**
     * Instance of API
     */
    public static final API client = new API();

    /**
     * The root of API found at {@value BASE_URL}
     */
    private static RootDTO root;

    /**
     * History of requests, allowing the user to retrieve a request
     * and avoid sending multiple requests to the server API.
     */
    public final LinkedList<Map.Entry<Connection, JsonObject>> requests;
    public final LinkedList<Connection> connections;

    private API(){
        this.requests = new LinkedList<>();
        this.connections = new LinkedList<>();
    }

    public RootDTO root() throws Exception {
        if(root == null){
            root = RootDTO.createFrom(this);
        }
        return root;
    }

    /**
     * Make a request to get the root of the API
     * @return root of {@value BASE_URL}
     * @throws Exception if it fails for any reason
     */
    public JsonObject requestRoot() throws Exception {
        return request(BASE_URL);
    }

    public JsonObject request(String url) throws IOException, InterruptedException, RequestFailedException {
        return request(URL.decode(url));
    }

    public JsonObject request(String path, String query) throws IOException, InterruptedException, RequestFailedException {
        return request(URL.build(BASE_URL,path,query));
    }

    public JsonObject request(URL url) throws IOException, InterruptedException, RequestFailedException {
        Connection currentConnection = Connection.connect(url);
        if(currentConnection.statusCode() != 200){
            throw new RequestFailedException(currentConnection, "Failed: HTTP error code: "+ currentConnection.statusCode());
        }

        JsonObject jsonObject = deserialize(currentConnection.getResponse().body());
        requests.add(Map.entry(currentConnection, jsonObject));
        return jsonObject;
    }

    private JsonObject deserialize(String json){
        return new Gson().fromJson(json, JsonObject.class);
    }


    public Map.Entry<Connection, JsonObject> getLastRequest(){
        return requests.getLast();
    }
}
