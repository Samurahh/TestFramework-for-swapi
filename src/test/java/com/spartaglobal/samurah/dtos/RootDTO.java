package com.spartaglobal.samurah.dtos;

import com.google.gson.Gson;
import com.spartaglobal.samurah.util.API;
import com.spartaglobal.samurah.util.URL;


public class RootDTO extends SwapiObject {

    private String films;
    private String people;
    private String planets;
    private String species;
    private String starships;
    private String vehicles;
    private API api = null;
    private String url;

    public RootDTO() {
    }

    public static RootDTO createFrom(API api) throws Exception {
        RootDTO root = new Gson().fromJson(API.client.requestRoot(), RootDTO.class);
        root.setAPI(api);
        root.setUrl(API.BASE_URL);
        return root;
    }

    @Override
    protected void setAPI(API api) {
        this.api = api;
    }

    public FilmsListDTO films() throws Exception {
        FilmsListDTO films = FilmsListDTO.createFrom(api.request(this.films));
        films.setAPI(api);
        films.setUrl(this.films);
        return films;
    }

    public PeopleListDTO people() throws Exception {
        PeopleListDTO people = PeopleListDTO.createFrom(api.request(this.people));
        people.setAPI(api);
        people.setUrl(this.people);
        return people;
    }

    public PlanetsListDTO planets() throws Exception {
        PlanetsListDTO planets = PlanetsListDTO.createFrom(api.request(this.planets));
        planets.setAPI(api);
        planets.setUrl(this.planets);
        return planets;
    }

    public SpeciesListDTO species() throws Exception {
        SpeciesListDTO species = SpeciesListDTO.createFrom(api.request(this.species));
        species.setAPI(api);
        species.setUrl(this.species);
        return species;
    }

    public VehiclesListDTO vehicles() throws Exception {
        VehiclesListDTO vehicles = VehiclesListDTO.createFrom(api.request(this.vehicles));
        vehicles.setAPI(api);
        vehicles.setUrl(this.vehicles);
        return vehicles;
    }

    public StarshipsListDTO starships() throws Exception {
        StarshipsListDTO starships = StarshipsListDTO.createFrom(api.request(this.starships));
        starships.setAPI(api);
        starships.setUrl(this.starships);
        return starships;
    }

    @Override
    protected void setUrl(String url) {
        this.url = url;
    }

    @Override
    protected URL getUrl() {
        return URL.decode(url);
    }

}
