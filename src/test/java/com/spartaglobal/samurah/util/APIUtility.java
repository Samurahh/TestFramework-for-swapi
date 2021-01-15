package com.spartaglobal.samurah.util;

import com.spartaglobal.samurah.dtos.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class APIUtility {

    public static boolean hasReference(FilmDTO film, URL reference){
        String[] references;
        switch (getTarget(reference.getPath())){
            case "people":{
                references = film.characters();
                break;
            }
            case "planets":{
                references = film.planets();
                break;
            }
            case "starships":{
                references = film.starships();
                break;
            }
            case "vehicles":{
                references = film.species();
                break;
            }
            case "species":{
                references = film.species();
                break;
            }
            default:{
                return false;
            }
        }
        return checkIfContains(reference.toString(), references);
    }

    public static boolean hasReference(PersonDTO person, URL reference){
        String[] references;
        switch (getTarget(reference.getPath())){
            case "films":{
                references = person.films();
                break;
            }
            case "species":{
                references = person.species();
                break;
            }
            case "vehicles":{
                references = person.vehicles();
                break;
            }
            case "starships":{
                references = person.starships();
                break;
            }
            default:{
                return false;
            }
        }
        return checkIfContains(reference.toString(), references);
    }

    public static boolean hasReference(PlanetDTO planet, URL reference){
        String[] references;
        switch (getTarget(reference.getPath())){
            case "people":{
                references = planet.residents();
                break;
            }
            case "films":{
                references = planet.films();
                break;
            }
            default:{
                return false;
            }
        }
        return checkIfContains(reference.toString(), references);
    }

    public static boolean hasReference(SpeciesDTO species, URL reference){
        String[] references;
        switch (getTarget(reference.getPath())){
            case "people":{
                references = species.people();
                break;
            }
            case "films":{
                references = species.films();
                break;
            }
            default:{
                return false;
            }
        }
        return checkIfContains(reference.toString(), references);
    }

    public static boolean hasReference(StarshipDTO starship, URL reference){
        String[] references;
        switch (getTarget(reference.getPath())){
            case "people":{
                references = starship.pilots();
                break;
            }
            case "films":{
                references = starship.films();
                break;
            }
            default:{
                return false;
            }
        }
        return checkIfContains(reference.toString(), references);
    }

    public static boolean hasReference(VehicleDTO vehicle, URL reference){
        String[] references;
        switch (getTarget(reference.getPath())){
            case "people":{
                references = vehicle.pilots();
                break;
            }
            case "films":{
                references = vehicle.films();
                break;
            }
            default:{
                return false;
            }
        }
        return checkIfContains(reference.toString(), references);
    }

    private static boolean checkIfContains(String target, String[] array){
        for (String s : array) {
            String reference = URL.decode(s).toString();
            if(reference.matches(".*"+target+".*")){
                return true;
            }
        }
        return false;
    }

    private static String getTarget(String path){
        Pattern pattern = Pattern.compile("api/?(?<target>\\w+)/?.*");
        Matcher matcher = pattern.matcher(path);
        String target = "";
        if(matcher.matches()){
           if(matcher.group("target")!=null){
               target = matcher.group("target");
           }
        }
        return target;
    }
}
