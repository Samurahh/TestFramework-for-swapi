package com.spartaglobal.samurah.util;

import com.spartaglobal.samurah.dtos.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class APIUtility {

    /**
     * Checks if {@code film} has {@code reference} in its body.
     * @param film object looked-up.
     * @param reference URL
     * @return return true if {@code film} has {@code reference} in
     * the appropriate category, else false
     */
    public static boolean hasReference(FilmDTO film, URL reference){
        String[] references;
        switch (getTarget(reference.getPath())){
            case "people":{
                references = film.charactersReferences();
                break;
            }
            case "planets":{
                references = film.planetsReferences();
                break;
            }
            case "starships":{
                references = film.starshipsReferences();
                break;
            }
            case "vehicles":{
                references = film.speciesReferences();
                break;
            }
            case "species":{
                references = film.speciesReferences();
                break;
            }
            default:{
                return false;
            }
        }
        if(references != null){
            return checkIfContains(reference.toString(), references);
        }else{
            return false;
        }
    }

    /**
     * Checks if {@code person} has {@code reference} in its body.
     * @param person object looked-up.
     * @param reference URL
     * @return return true if {@code person} has {@code reference} in
     * the appropriate category, else false
     */
    public static boolean hasReference(PersonDTO person, URL reference){
        String[] references;
        switch (getTarget(reference.getPath())){
            case "films":{
                references = person.filmsReferences();
                break;
            }
            case "species":{
                references = person.speciesReferences();
                break;
            }
            case "vehicles":{
                references = person.vehiclesReferences();
                break;
            }
            case "starships":{
                references = person.starshipsReferences();
                break;
            }
            default:{
                return false;
            }
        }
        if(references != null){
            return checkIfContains(reference.toString(), references);
        }else{
            return false;
        }
    }

    /**
     * Checks if {@code planet} has {@code reference} in its body.
     * @param planet object looked-up.
     * @param reference URL
     * @return return true if {@code planet} has {@code reference} in
     * the appropriate category, else false
     */
    public static boolean hasReference(PlanetDTO planet, URL reference){
        String[] references;
        switch (getTarget(reference.getPath())){
            case "people":{
                references = planet.residentsReferences();
                break;
            }
            case "films":{
                references = planet.filmsReferences();
                break;
            }
            default:{
                return false;
            }
        }
        if(references != null){
            return checkIfContains(reference.toString(), references);
        }else{
            return false;
        }
    }

    /**
     * Checks if {@code species} has {@code reference} in its body.
     * @param species object looked-up.
     * @param reference URL
     * @return return true if {@code species} has {@code reference} in
     * the appropriate category, else false
     */
    public static boolean hasReference(SpeciesDTO species, URL reference){
        String[] references;
        switch (getTarget(reference.getPath())){
            case "people":{
                references = species.peopleReferences();
                break;
            }
            case "films":{
                references = species.filmsReferences();
                break;
            }
            default:{
                return false;
            }
        }
        if(references != null){
            return checkIfContains(reference.toString(), references);
        }else{
            return false;
        }
    }

    /**
     * Checks if {@code starship} has {@code reference} in its body.
     * @param starship object looked-up.
     * @param reference URL
     * @return return true if {@code starship} has {@code reference} in
     * the appropriate category, else false
     */
    public static boolean hasReference(StarshipDTO starship, URL reference){
        String[] references;
        switch (getTarget(reference.getPath())){
            case "people":{
                references = starship.pilotsReferences();
                break;
            }
            case "films":{
                references = starship.filmsReferences();
                break;
            }
            default:{
                return false;
            }
        }
        if(references != null){
            return checkIfContains(reference.toString(), references);
        }else{
            return false;
        }
    }

    /**
     * Checks if {@code vehicle} has {@code reference} in its body.
     * @param vehicle object looked-up.
     * @param reference URL
     * @return return true if {@code vehicle} has {@code reference} in
     * the appropriate category, else false
     */
    public static boolean hasReference(VehicleDTO vehicle, URL reference){
        String[] references;
        switch (getTarget(reference.getPath())){
            case "people":{
                references = vehicle.pilotsReferences();
                break;
            }
            case "films":{
                references = vehicle.filmsReferences();
                break;
            }
            default:{
                return false;
            }
        }
        if(references != null){
            return checkIfContains(reference.toString(), references);
        }else{
            return false;
        }
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
