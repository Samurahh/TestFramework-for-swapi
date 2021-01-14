package com.spartaglobal.framework.resourcetype;

import com.spartaglobal.framework.dtos.*;
import com.spartaglobal.framework.util.URLdecoder;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ResourceType {
    ROOT(RootDTO.class, "api/"),
    PERSON(PersonDTO.class, "api/people/{}"),
    PEOPLE_LIST(PeopleListDTO.class, "api/people/"),
    FILM(FilmDTO.class, "api/films/{}"),
    FILMS_LIST(FilmsListDTO.class, "api/films/"),
    STARSHIP(StarshipDTO.class, "api/starships/{}"),
    STARSHIPS_LIST(StarshipsListDTO.class, "api/starships/"),
    VEHICLE(VehicleDTO.class, "api/vehicles/{}"),
    VEHICLES_LIST(VehiclesListDTO.class, "api/vehicles/"),
    SPECIES(SpeciesDTO.class, "api/species/{}"),
    SPECIES_LIST(SpeciesListDTO.class, "api/species/"),
    PLANET(PlanetDTO.class, "api/planets/{}"),
    PLANETS_LIST(PlanetsListDTO.class, "api/planets/");

    private static HashMap<String, ResourceType> resourceTypeMap; //Map(path,ResourceType)
    private final Class<? extends SwapiObject> objectType;
    private final String path;

    ResourceType(Class<? extends SwapiObject> objectType, String path) {
        this.objectType = objectType;
        this.path = path;
    }

    public static ResourceType getResourceType(String path){
        if(resourceTypeMap == null){
            initResourceMap();
        }
        if(resourceTypeMap.containsKey(path)){
            return resourceTypeMap.get(path);
        }// returns if it finds a _LIST

        Pattern pattern = Pattern.compile("(?<path>[^\\d]+).*");
        Matcher matcher = pattern.matcher(path);
        if(matcher.matches()){
            path = matcher.group("path")+"{}";
        }// adjust the path to match resourceTypeMap

        return resourceTypeMap.get(path);
    }

    public static Class<? extends SwapiObject> getClassOf(String path) {
        return getResourceType(path).getObjectType();
    }

    public Class<? extends SwapiObject> getObjectType() {
        return objectType;
    }

    public String getPath(){
        return path;
    }

    private static void initResourceMap(){
        resourceTypeMap = new HashMap<>();
        for(ResourceType resourceType: ResourceType.values()){
            resourceTypeMap.put(resourceType.getPath(), resourceType);
        }
    }
}
