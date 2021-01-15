package com.spartaglobal.samurah.dtos;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spartaglobal.samurah.util.API;
import com.spartaglobal.samurah.util.URL;

public class StarshipDTO extends SwapiObject {

    private String name;
    private String model;
    private String starship_class;
    private String manufacturer;
    private String cost_in_credits;
    private String length;
    private String crew;
    private String passengers;
    private String max_atmosphering_speed;
    private String MGLT;
    private String cargo_capacity;
    private String consumables;
    private String[] films;
    private String[] pilots;
    private String url;
    private String created;
    private String edited;
    private API api = API.client;

    private StarshipDTO() {
    }

    public static StarshipDTO createFrom(JsonObject value, API api) {
        StarshipDTO starshipDTO = new Gson().fromJson(value.toString(), StarshipDTO.class);
        starshipDTO.setAPI(api);
        return starshipDTO;
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

    public String getModel() {
        return model;
    }

    public String getStarshipClass() {
        return starship_class;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getCostInCredits() {
        return cost_in_credits;
    }

    public String getLength() {
        return length;
    }

    public String getCrew() {
        return crew;
    }

    public String getPassengers() {
        return passengers;
    }

    public String getMaxAatmospheringSpeed() {
        return max_atmosphering_speed;
    }

    public String getMGLT() {
        return MGLT;
    }

    public String getCargo_capacity() {
        return cargo_capacity;
    }

    public String getConsumables() {
        return consumables;
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

    public boolean hasPilots(){
        return pilots.length > 0;
    }

    public String[] pilots() {
        return pilots;
    }

    public PersonDTO pilot(int id) throws Exception {
        if(id<pilots.length){
            return PersonDTO.createFrom(api.request(pilots[id]), api);
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
