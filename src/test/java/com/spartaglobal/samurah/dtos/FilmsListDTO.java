package com.spartaglobal.samurah.dtos;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spartaglobal.samurah.interfaces.ListInterface;
import com.spartaglobal.samurah.util.API;
import com.spartaglobal.samurah.util.URL;

public class FilmsListDTO extends SwapiObject implements ListInterface {

    public FilmsListDTO(){}

    private int count;
    private String next;
    private String previous;
    private FilmDTO[] results;
    private API api = API.client;
    private String url;

    public static FilmsListDTO createFrom(JsonObject value) {
        return new Gson().fromJson(value.toString(), FilmsListDTO.class);
    }

    public static FilmsListDTO createFrom(JsonObject value, API api) {
        FilmsListDTO filmsListDTO = new Gson().fromJson(value.toString(), FilmsListDTO.class);
        filmsListDTO.setAPI(api);
        return filmsListDTO;
    }

    @Override
    public FilmsListDTO next() throws Exception {
        if(this.next != null){
            return FilmsListDTO.createFrom(api.request(this.next));
        }
        return null;
    }

    @Override
    public FilmsListDTO previous() throws Exception {
        if(this.previous != null){
            return FilmsListDTO.createFrom(api.request(this.previous));
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

    public static FilmsListDTO search(String searchName, API api) throws Exception {
        URL queryUrl = api.root().films().getUrl();
        return FilmsListDTO.createFrom(api.request(queryUrl.query(searchName)), api);
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

    public FilmDTO[] getResults() {
        return results;
    }

}