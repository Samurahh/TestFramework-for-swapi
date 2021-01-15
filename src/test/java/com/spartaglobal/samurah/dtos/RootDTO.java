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
    private API api = API.client;
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
        FilmsListDTO films = FilmsListDTO.createFrom(api.request(this.films), api);
        films.setUrl(this.films);
        return films;
    }

    public FilmDTO film(int id) throws Exception {
        URL url = URL.decode(films).appendToPath(String.valueOf(id));
        return FilmDTO.createFrom(api.request(url),api);
    }

    public PeopleListDTO people() throws Exception {
        PeopleListDTO people = PeopleListDTO.createFrom(api.request(this.people), api);
        people.setUrl(this.people);
        return people;
    }

    public PersonDTO person(int id) throws Exception {
        URL url = URL.decode(films).appendToPath(String.valueOf(id));
        return PersonDTO.createFrom(api.request(url),api);
    }

    public PlanetsListDTO planets() throws Exception {
        PlanetsListDTO planets = PlanetsListDTO.createFrom(api.request(this.planets), api);
        planets.setUrl(this.planets);
        return planets;
    }

    public PlanetDTO planet(int id) throws Exception {
        URL url = URL.decode(films).appendToPath(String.valueOf(id));
        return PlanetDTO.createFrom(api.request(url),api);
    }

    public SpeciesListDTO species() throws Exception {
        SpeciesListDTO species = SpeciesListDTO.createFrom(api.request(this.species), api);
        species.setUrl(this.species);
        return species;
    }

    public SpeciesDTO species(int id) throws Exception {
        URL url = URL.decode(films).appendToPath(String.valueOf(id));
        return SpeciesDTO.createFrom(api.request(url),api);
    }

    public VehiclesListDTO vehicles() throws Exception {
        VehiclesListDTO vehicles = VehiclesListDTO.createFrom(api.request(this.vehicles), api);
        vehicles.setUrl(this.vehicles);
        return vehicles;
    }

    public VehicleDTO vehicle(int id) throws Exception {
        URL url = URL.decode(films).appendToPath(String.valueOf(id));
        return VehicleDTO.createFrom(api.request(url),api);
    }

    public StarshipsListDTO starships() throws Exception {
        StarshipsListDTO starships = StarshipsListDTO.createFrom(api.request(this.starships), api);
        starships.setUrl(this.starships);
        return starships;
    }

    public StarshipDTO starship(int id) throws Exception {
        URL url = URL.decode(films).appendToPath(String.valueOf(id));
        return StarshipDTO.createFrom(api.request(url),api);
    }

    public int calculateTotalFiles() throws Exception {
        return films().getCount() + people().getCount() + planets().getCount() +
                species().getCount() + vehicles().getCount() + starships().getCount();
    }

    @Override
    protected void setUrl(String url) {
        this.url = url;
    }

    @Override
    public URL getUrl() {
        return URL.decode(url);
    }

}
