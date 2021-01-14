package com.spartaglobal.samurah.dtos;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spartaglobal.samurah.interfaces.ListInterface;
import com.spartaglobal.samurah.util.API;
import com.spartaglobal.samurah.util.URL;

public class PeopleListDTO extends SwapiObject implements ListInterface {

    private int count;
    private String next;
    private String previous;
    private PersonDTO[] results;
    private API api = null;
    private String url;

    public PeopleListDTO(){
    }

    @Override
    public PeopleListDTO next() throws Exception {
        if(this.next != null){
            return PeopleListDTO.createFrom(api.request(this.next), api);
        }
        return this;
    }

    @Override
    public PeopleListDTO previous() throws Exception {
        if(this.previous != null){
            return PeopleListDTO.createFrom(api.request(this.previous), api);
        }
        return this;
    }

    @Override
    public boolean hasNext() {
        return this.next != null;
    }

    @Override
    public boolean hasPrevious() {
        return this.previous != null;
    }

    public static PeopleListDTO createFrom(JsonObject value) {
        return new Gson().fromJson(value.toString(), PeopleListDTO.class);
    }

    public static PeopleListDTO createFrom(JsonObject value, API api) {
        PeopleListDTO peopleListDTO = new Gson().fromJson(value.toString(), PeopleListDTO.class);
        peopleListDTO.setAPI(api);
        return peopleListDTO;
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
    protected URL getUrl() {
        return URL.decode(url);
    }

    public static PeopleListDTO search(String searchName, API api) throws Exception {
        URL queryUrl = api.root().people().getUrl();
        return PeopleListDTO.createFrom(api.request(queryUrl.query(searchName)), api);
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

    public PersonDTO[] getResults() {
        return results;
    }

    public void setResults(PersonDTO[] results) {
        this.results = results;
    }
}
