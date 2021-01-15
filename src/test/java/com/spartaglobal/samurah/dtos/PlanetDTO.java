package com.spartaglobal.samurah.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spartaglobal.samurah.exceptions.RequestFailedException;
import com.spartaglobal.samurah.util.API;
import com.spartaglobal.samurah.util.URL;

import java.io.IOException;

public class PlanetDTO extends SwapiObject {

    @JsonIgnore
    public static String referenceName = "planets";

    private String name;
    private String diameter;
    private String rotation_period;
    private String orbital_period;
    private String gravity;
    private String population;
    private String climate;
    private String terrain;
    private String surface_water;
    private String[] residents;
    private String[] films;
    private String url;
    private String created;
    private String edited;
    private API api = API.client;

    public PlanetDTO() {
    }

    public static PlanetDTO createFrom(JsonObject value, API api) {
        PlanetDTO planetDTO = new Gson().fromJson(value.toString(), PlanetDTO.class);
        planetDTO.setAPI(api);
        return planetDTO;
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

    public String getDiameter() {
        return diameter;
    }

    public String getRotationPeriod() {
        return rotation_period;
    }

    public String getOrbitalPeriod() {
        return orbital_period;
    }

    public String getGravity() {
        return gravity;
    }

    public String getPopulation() {
        return population;
    }

    public String getClimate() {
        return climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public String getSurfaceWater() {
        return surface_water;
    }

    public boolean hasResidents(){
        return residents.length > 0;
    }

    public String[] residentsReferences() {
        return residents;
    }

    public PersonDTO[] residents() throws InterruptedException, RequestFailedException, IOException {
        PersonDTO[] objects = new PersonDTO[residents.length];
        for (int i = 0; i < residents.length; i++) {
            objects[i] = PersonDTO.createFrom(api.request(residents[i]), api);
        }
        return objects;
    }

    public PersonDTO resident(int id) throws Exception {
        if (id < residents.length) {
            return PersonDTO.createFrom(api.request(residents[id]), api);
        }
        return null;
    }

    public boolean hasFilms(){
        return films.length > 0;
    }

    public String[] filmsReferences() {
        return films;
    }

    public FilmDTO[] films() throws InterruptedException, RequestFailedException, IOException {
        FilmDTO[] objects = new FilmDTO[films.length];
        for (int i = 0; i < films.length; i++) {
            objects[i] = FilmDTO.createFrom(api.request(films[i]), api);
        }
        return objects;
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
