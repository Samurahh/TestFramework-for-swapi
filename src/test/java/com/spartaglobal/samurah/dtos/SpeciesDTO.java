package com.spartaglobal.samurah.dtos;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spartaglobal.samurah.util.API;
import com.spartaglobal.samurah.util.URL;

public class SpeciesDTO extends SwapiObject {

    private String name;
    private String classification;
    private String average_height;
    private String average_lifespan;
    private String eye_colors;
    private String hair_colors;
    private String skin_colors;
    private String language;
    private String homeworld;
    private String[] people;
    private String[] films;
    private String url;
    private String created;
    private String edited;
    private API api = API.client;

    private SpeciesDTO() {
    }

    public static SpeciesDTO createFrom(JsonObject value, API api) {
        SpeciesDTO speciesDTO = new Gson().fromJson(value.toString(), SpeciesDTO.class);
        speciesDTO.setAPI(api);
        return speciesDTO;
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

    public String getName() {
        return name;
    }

    public String getClassification() {
        return classification;
    }

    public String getAverageHeight() {
        return average_height;
    }

    public String getAverageLifespan() {
        return average_lifespan;
    }

    public String getEyeColors() {
        return eye_colors;
    }

    public String getHairColors() {
        return hair_colors;
    }

    public String getSkinColors() {
        return skin_colors;
    }

    public String getLanguage() {
        return language;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public boolean hasPeople(){
        return people.length > 0;
    }

    public String[] people() {
        return people;
    }

    public PersonDTO person(int id) throws Exception {
        if(id<people.length){
            return PersonDTO.createFrom(api.request(people[id]), api);
        }
        return null;
    }

    public boolean hasFilms(){
        return films.length > 0;
    }

    public String[] films() {
        return films;
    }

    public FilmDTO film(int id) throws Exception {
        if(id<films.length){
            return FilmDTO.createFrom(api.request(films[id]), api);
        }
        return null;
    }

    public String getCreated() {
        return created;
    }

    public String getEdited() {
        return edited;
    }

}
