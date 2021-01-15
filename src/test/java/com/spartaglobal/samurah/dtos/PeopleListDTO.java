package com.spartaglobal.samurah.dtos;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spartaglobal.samurah.interfaces.ListInterface;
import com.spartaglobal.samurah.util.API;
import com.spartaglobal.samurah.util.URL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class PeopleListDTO extends SwapiObject implements ListInterface {

    private int count;
    private String next;
    private String previous;
    private PersonDTO[] results;
    private API api = API.client;
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
    public URL getUrl() {
        return URL.decode(url);
    }

    public static PeopleListDTO search(String searchName, API api) throws Exception {
        URL queryUrl = api.root().people().getUrl().query(searchName);
        PeopleListDTO peopleListDTO = PeopleListDTO.createFrom(api.request(queryUrl), api);
        peopleListDTO.setUrl(queryUrl.toString());
        return peopleListDTO;
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

    public PersonDTO[] getResults() {
        return results;
    }

    public Collection<PersonDTO> getAll(){
        ArrayList<PersonDTO> all = new ArrayList<>(Arrays.asList(results));
        if(this.hasPrevious()) {
            try {
                addToListPrevious(all, this.previous());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(this.hasNext()) {
            try {
                addToListNext(all, this.next());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return all;
    }

    private void addToListPrevious(Collection<PersonDTO> list, PeopleListDTO peopleListDTO){
        list.addAll(Arrays.asList(peopleListDTO.results));
        if(peopleListDTO.hasPrevious()) {
            try {
                addToListPrevious(list, peopleListDTO.previous());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void addToListNext(Collection<PersonDTO> list, PeopleListDTO peopleListDTO){
        list.addAll(Arrays.asList(peopleListDTO.results));
        if(peopleListDTO.hasNext()) {
            try {
                addToListNext(list, peopleListDTO.next());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
