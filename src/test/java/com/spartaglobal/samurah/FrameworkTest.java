package com.spartaglobal.samurah;

import com.google.gson.Gson;
import com.spartaglobal.samurah.dtos.*;
import com.spartaglobal.samurah.exceptions.RequestFailedException;
import com.spartaglobal.samurah.util.API;
import com.spartaglobal.samurah.util.APIUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class FrameworkTest {


    /*
    The framework deals with:
    - URL formats (building, decoding)
    - Resource references (resource location extracted from root)
    - Search query for any resource
    - API connections
    - Object injections
    - APIUtility - offers hasReference(SwapiObject target, URL reference)
    that allows user to check if the target contains the url reference at the right
    category in its body.
     */

    static HttpClient httpClient;

    @BeforeAll
    @DisplayName("WITHOUT: Setup")
    static void withGettingTheRootOfApi() {
        httpClient = HttpClient.newHttpClient();
    }

    @Test
    @DisplayName("WITHOUT: Check if film 1 has a title")
    void checkIfRootHasReferenceToAllListsOfObjects() throws IOException, InterruptedException {
        String rootUrl = "https://swapi.dev/api/films/1/";
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(rootUrl)).build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        FilmDTO filmDTO = new Gson().fromJson(httpResponse.body(), FilmDTO.class);
        Assertions.assertFalse(filmDTO.getTitle().isBlank());
    }

    @Test
    @DisplayName("WITH: Check if film 1 has a title")
    void withCheckIfFilm1HasATitle() throws Exception {
        Assertions.assertFalse(API.client.root().film(1).getTitle().isBlank());
    }

    @Test
    @DisplayName("WITHOUT: Searching for vehicles name,model containing \"b\" should return 9")
    void withoutSearchingForVehiclesNameModelContainingBShouldReturn9() throws IOException, InterruptedException {
        String vehicleQuery = "https://swapi.dev/api/vehicles/?search=b";
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(vehicleQuery)).build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        VehiclesListDTO vehiclesListDTO = new Gson().fromJson(httpResponse.body(), VehiclesListDTO.class);
        Assertions.assertEquals(9, vehiclesListDTO.getCount());
    }

    @Test
    @DisplayName("WITH: Searching for vehicles name,model containing \"b\" should return 9")
    void withSearchingForVehiclesNameModelContainingBShouldReturn9() throws Exception {
        Assertions.assertEquals(9, API.client.root().vehicles("b").getCount());
    }

    @Test
    @DisplayName("WITHOUT: Check the total number of files in API accessible from ROOT")
    void withoutCheckTheTotalNumberOfFilesInApiAccessibleFromRoot() throws IOException, InterruptedException {
        String films = "https://swapi.dev/api/films/";
        String people = "https://swapi.dev/api/people/";
        String planets = "https://swapi.dev/api/planets/";
        String species = "https://swapi.dev/api/species/";
        String vehicles = "https://swapi.dev/api/vehicles/";
        String starships = "https://swapi.dev/api/starships/";
        HttpRequest filmsRequest = HttpRequest.newBuilder().uri(URI.create(films)).build();
        HttpResponse<String> httpResponse = httpClient.send(filmsRequest, HttpResponse.BodyHandlers.ofString());
        FilmsListDTO filmsListDTO = new Gson().fromJson(httpResponse.body(), FilmsListDTO.class);

        HttpRequest peopleRequest = HttpRequest.newBuilder().uri(URI.create(people)).build();
        httpResponse = httpClient.send(peopleRequest, HttpResponse.BodyHandlers.ofString());
        PeopleListDTO peopleListDTO = new Gson().fromJson(httpResponse.body(), PeopleListDTO.class);

        HttpRequest planetsRequest = HttpRequest.newBuilder().uri(URI.create(planets)).build();
        httpResponse = httpClient.send(planetsRequest, HttpResponse.BodyHandlers.ofString());
        PlanetsListDTO planetsListDTO = new Gson().fromJson(httpResponse.body(), PlanetsListDTO.class);

        HttpRequest speciesRequest = HttpRequest.newBuilder().uri(URI.create(species)).build();
        httpResponse = httpClient.send(speciesRequest, HttpResponse.BodyHandlers.ofString());
        SpeciesListDTO speciesListDTO = new Gson().fromJson(httpResponse.body(), SpeciesListDTO.class);

        HttpRequest vehiclesRequest = HttpRequest.newBuilder().uri(URI.create(vehicles)).build();
        httpResponse = httpClient.send(vehiclesRequest, HttpResponse.BodyHandlers.ofString());
        VehiclesListDTO vehiclesListDTO = new Gson().fromJson(httpResponse.body(), VehiclesListDTO.class);


        HttpRequest starshipsRequest = HttpRequest.newBuilder().uri(URI.create(starships)).build();
        httpResponse = httpClient.send(starshipsRequest, HttpResponse.BodyHandlers.ofString());
        StarshipsListDTO starshipsListDTO = new Gson().fromJson(httpResponse.body(), StarshipsListDTO.class);

        int count = filmsListDTO.getCount() + peopleListDTO.getCount() + planetsListDTO.getCount() +
                speciesListDTO.getCount() + vehiclesListDTO.getCount() + starshipsListDTO.getCount();

        Assertions.assertEquals(260, count);
    }

    @Test
    @DisplayName("WITH: Check the total number of files in API accessible from ROOT")
    void withCheckTheTotalNumberOfFilesInApiAccessibleFromRoot() throws Exception {
        Assertions.assertEquals(260, API.client.root().calculateTotalFiles());
    }


    /*
    if FILMS/1 has reference of PLANETS/1
    PLANETS/1 should have reference of FILMS/1
     */
    @Test
    @DisplayName("WITH: Check mutual reference")
    void withCheckMutualReference() throws Exception {
        boolean filmHasPlanet = APIUtility.hasReference(API.client.root().film(1), API.client.root().planet(1).getUrl());
        boolean planetHasFilm = APIUtility.hasReference(API.client.root().planet(1), API.client.root().film(1).getUrl());
        Assertions.assertTrue(planetHasFilm && filmHasPlanet);
    }

    @Test
    @DisplayName("WITH: getAll")
    void withGetAll() throws Exception {
        Assertions.assertEquals(API.client.root().vehicles().getCount(), API.client.root().vehicles().getAll().size());
    }

    @Test
    @DisplayName("WITH: test connection status")
    void withTestConnectionStatus(){
        try {
            API.client.request("http://swapi.dev/api/people/");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } catch (RequestFailedException e) {
            Assertions.fail();
        }
    }
}
