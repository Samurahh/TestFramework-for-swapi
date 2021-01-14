package com.spartaglobal.samurah.dtos;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spartaglobal.samurah.interfaces.ListInterface;
import com.spartaglobal.samurah.util.API;
import com.spartaglobal.samurah.util.URL;

public class PlanetsListDTO extends SwapiObject implements ListInterface {

    public PlanetsListDTO(){}

    private int count;
    private String next;
    private String previous;
    private PlanetDTO[] results;
    private API api = null;
    private String url;

    @Override
    protected void setAPI(API api) {
        this.api = api;
    }

    @Override
    protected void setUrl(String url) {
        this.url = url;
    }

    @Override
    protected URL getUrl() {
        return URL.decode(url);
    }

    public static PlanetsListDTO createFrom(JsonObject value) {
        return new Gson().fromJson(value.toString(), PlanetsListDTO.class);
    }

    public static PlanetsListDTO createFrom(JsonObject value, API api) {
        PlanetsListDTO planetsListDTO = new Gson().fromJson(value.toString(),PlanetsListDTO.class);
        planetsListDTO.setAPI(api);
        return planetsListDTO;
    }

    @Override
    public PlanetsListDTO next() throws Exception {
        if(this.next != null){
            return PlanetsListDTO.createFrom(api.request(this.next), api);
        }
        return null;
    }

    @Override
    public PlanetsListDTO previous() throws Exception {
        if(this.previous != null){
            return PlanetsListDTO.createFrom(api.request(this.previous), api);
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

    public static PlanetsListDTO search(String searchName, API api) throws Exception {
        URL queryUrl = api.root().planets().getUrl();
        return PlanetsListDTO.createFrom(api.request(queryUrl.query(searchName)), api);
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

    public PlanetDTO[] getResults() {
        return results;
    }

    public void setResults(PlanetDTO[] results) {
        this.results = results;
    }

}
