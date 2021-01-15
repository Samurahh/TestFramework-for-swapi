package com.spartaglobal.samurah.dtos;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spartaglobal.samurah.util.API;
import com.spartaglobal.samurah.util.URL;

import java.io.IOException;

public class FilmDTO extends SwapiObject {

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
    private API api = API.client;


    private FilmDTO() {
    }

    public static FilmDTO createFrom(JsonObject value, API api) {
        FilmDTO filmDTO = new Gson().fromJson(value, FilmDTO.class);
        filmDTO.setAPI(api);
        return filmDTO;
    }

    @Override
    protected void setAPI(API api) {
        this.api = api;
    }

    @Override
    public URL getUrl() {
        return URL.decode(url);
    }

    @Override
    protected void setUrl(String url) {
        this.url = url;
    }

    public boolean hasSpecies(){
        return species.length > 0;
    }

    public String[] species() {
        return species;
    }

    public SpeciesDTO species(int id) throws IOException, InterruptedException {
        if (id < species.length) {
            return SpeciesDTO.createFrom(api.request(species[id]), api);
        }
        return null;
    }

    public boolean hasPlanets(){
        return planets.length > 0;
    }

    public String[] planets() {
        return planets;
    }

    public PlanetDTO planet(int id) throws Exception {
        if (id < planets.length) {
            return PlanetDTO.createFrom(api.request(planets[id]), api);
        }
        return null;
    }

    public boolean hasStarships(){
        return planets.length > 0;
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

    public boolean hasCharacters(){
        return characters.length > 0;
    }

    public String[] characters() {
        return characters;
    }

    public PersonDTO character(int id) throws Exception {
        if (id < characters.length) {
            return PersonDTO.createFrom(api.request(characters[id]), api);
        }
        return null;
    }

    public String getTitle() {
        return title;
    }

    public int getEpisodeId() {
        return episode_id;
    }

    public String getOpeningCrawl() {
        return opening_crawl;
    }

    public String getDirector() {
        return director;
    }

    public String getProducer() {
        return producer;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public String getCreated() {
        return created;
    }

    public String getEdited() {
        return edited;
    }
}
