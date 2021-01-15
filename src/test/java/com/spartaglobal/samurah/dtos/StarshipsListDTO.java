package com.spartaglobal.samurah.dtos;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spartaglobal.samurah.interfaces.ListInterface;
import com.spartaglobal.samurah.util.API;
import com.spartaglobal.samurah.util.URL;

public class StarshipsListDTO extends SwapiObject implements ListInterface {

    public StarshipsListDTO(){}

    private int count;
    private String next;
    private String previous;
    private StarshipDTO[] results;
    private API api = API.client;
    private String url;

    public static StarshipsListDTO createFrom(JsonObject value) {
        return new Gson().fromJson(value.toString(), StarshipsListDTO.class);
    }

    public static StarshipsListDTO createFrom(JsonObject value, API api) {
        StarshipsListDTO starshipsListDTO = new Gson().fromJson(value.toString(),StarshipsListDTO.class);
        starshipsListDTO.setAPI(api);
        return starshipsListDTO;
    }

    @Override
    public StarshipsListDTO next() throws Exception {
        if(this.next != null){
            return StarshipsListDTO.createFrom(api.request(this.next), api);
        }
        return null;
    }

    @Override
    public StarshipsListDTO previous() throws Exception {
        if(this.previous != null){
            return StarshipsListDTO.createFrom(api.request(this.previous), api);
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return this.next != null;
    }

    @Override
    public boolean hasPrevious() {
        return this.previous != null;
    }

    @Override
    protected void setAPI(API api) {
        this.api = api;
    }

    @Override
    protected void setUrl(String url) {
        this.url = url;
    }

    @Override
    public URL getUrl() {
        return URL.decode(url);
    }

    public static StarshipsListDTO search(String searchName, API api) throws Exception {
        URL queryUrl = api.root().starships().getUrl();
        return StarshipsListDTO.createFrom(api.request(queryUrl.query(searchName)), api);
    }

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public boolean hasResults(){
        return results.length > 0;
    }

    public StarshipDTO[] getResults() {
        return results;
    }
}
