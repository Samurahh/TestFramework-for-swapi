package com.spartaglobal.samurah.dtos;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spartaglobal.samurah.interfaces.ListInterface;
import com.spartaglobal.samurah.util.API;
import com.spartaglobal.samurah.util.URL;

public class SpeciesListDTO extends SwapiObject implements ListInterface {

    public SpeciesListDTO(){}

    private int count;
    private String next;
    private String previous;
    private SpeciesDTO[] results;
    private API api = API.client;
    private String url;

    public static SpeciesListDTO createFrom(JsonObject value) {
        return new Gson().fromJson(value.toString(), SpeciesListDTO.class);
    }

    public static SpeciesListDTO createFrom(JsonObject value, API api) {
        SpeciesListDTO speciesListDTO = new Gson().fromJson(value.toString(),SpeciesListDTO.class);
        speciesListDTO.setAPI(api);
        return speciesListDTO;
    }

    @Override
    public SpeciesListDTO next() throws Exception {
        if(this.next != null){
            return SpeciesListDTO.createFrom(api.request(this.next), api);
        }
        return null;
    }

    @Override
    public SpeciesListDTO previous() throws Exception {
        if(this.previous != null){
            return SpeciesListDTO.createFrom(api.request(this.previous), api);
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

    public static SpeciesListDTO search(String searchName, API api) throws Exception {
        URL queryUrl = api.root().species().getUrl();
        return SpeciesListDTO.createFrom(api.request(queryUrl.query(searchName)), api);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public boolean hasResults(){
        return results.length > 0;
    }

    public SpeciesDTO[] getResults() {
        return results;
    }

    public void setResults(SpeciesDTO[] results) {
        this.results = results;
    }
}
