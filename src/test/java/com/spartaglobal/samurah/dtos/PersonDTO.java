package com.spartaglobal.samurah.dtos;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spartaglobal.samurah.util.API;
import com.spartaglobal.samurah.util.URL;

public class PersonDTO extends SwapiObject {

    public static String referenceName = "people";

    private String name;
    private String birth_year;
    private String eye_color;
    private String gender;
    private String hair_color;
    private String height;
    private String mass;
    private String skin_color;
    private String homeworld;
    private String[] films;
    private String[] species;
    private String[] starships;
    private String[] vehicles;
    private String url;
    private String created;
    private String edited;
    private API api = API.client;

    public PersonDTO(){}

    public static PersonDTO createFrom(JsonObject value, API api) {
        PersonDTO personDTO = new Gson().fromJson(value.toString(), PersonDTO.class);
        personDTO.setAPI(api);
        return personDTO;
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

    public String getBirth_year() {
        return birth_year;
    }

    public String getEye_color() {
        return eye_color;
    }

    public String getGender() {
        return gender;
    }

    public String getHair_color() {
        return hair_color;
    }

    public String getHeight() {
        return height;
    }

    public String getMass() {
        return mass;
    }

    public String getSkin_color() {
        return skin_color;
    }

    public String getHomeworld() {
        return homeworld;
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

    public boolean hasSpecies(){
        return species.length > 0;
    }

    public String[] species() {
        return species;
    }

    public SpeciesDTO species(int id) throws Exception {
        if (id < species.length) {
            return SpeciesDTO.createFrom(api.request(species[id]), api);
        }
        return null;
    }

    public boolean hasStarships(){
        return starships.length > 0;
    }

    public String[] starships() {
        return starships;
    }

    public StarshipDTO starship(int id) throws Exception {
        if (id < starships.length) {
            return StarshipDTO.createFrom(api.request(starships[id]), api);
        }
        return null;
    }

    public boolean hasVehicles(){
        return vehicles.length > 0;
    }

    public String[] vehicles() {
        return vehicles;
    }

    public VehicleDTO vehicle(int id) throws Exception {
        if (id < starships.length) {
            return VehicleDTO.createFrom(api.request(vehicles[id]), api);
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
