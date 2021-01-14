package com.spartaglobal.samurah.dtos;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spartaglobal.samurah.util.API;
import com.spartaglobal.samurah.util.URL;

import java.io.IOException;

public class FilmDTO extends SwapiObject {

    public static String referenceName = "films";

    private String title;
    private int episode_id;
    private String opening_crawl;
    private String director;
    private String producer;
    private String release_date;
    private String[] species;
    private String[] planets;
    private String[] starships;
    private String[] vehicles;
    private String[] characters;
    private String url;
    private String created;
    private String edited;
    private API api = null;


    public FilmDTO() {
    }

    public static FilmDTO createFrom(JsonObject value) {
        return new Gson().fromJson(value, FilmDTO.class);
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

    public String[] species() {
        return species;
    }

    public SpeciesDTO species(int id) throws IOException, InterruptedException {
        if(id<species.length){
            return SpeciesDTO.createFrom(api.request(species[id]), api);
        }
        return null;
    }

    public String[] planets() {
        return planets;
    }

    public PlanetDTO planet(int id) throws Exception {
        if(id<planets.length){
            return PlanetDTO.createFrom(api.request(planets[id]), api);
        }
        return null;
    }

    public String[] starships() {
        return starships;
    }

    public StarshipDTO starship(int id) throws Exception{
        if(id<starships.length){
            return StarshipDTO.createFrom(api.request(starships[id]), api);
        }
        return null;
    }

    public String[] vehicles() {
        return vehicles;
    }

    public VehicleDTO vehicle(int id) throws Exception{
        if(id<starships.length){
            return VehicleDTO.createFrom(api.request(vehicles[id]), api);
        }
        return null;
    }

    public String[] characters() {
        return characters;
    }

    public PersonDTO character(int id) throws Exception{
        if(id<characters.length){
            return PersonDTO.createFrom(api.request(characters[id]),api);
        }
        return null;
    }


}
