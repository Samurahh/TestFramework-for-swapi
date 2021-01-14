package com.spartaglobal.samurah.dtos;

import com.spartaglobal.samurah.util.API;
import com.spartaglobal.samurah.util.URL;

public abstract class SwapiObject {
    public SwapiObject(){}

    @SuppressWarnings("unchecked")
    public <T extends SwapiObject> T castTo(Class<T> objectType){
        return (T) this;
    }

    public FilmDTO castToFilmDTO(){
        if(this instanceof FilmDTO) {
            return (FilmDTO) this;
        }
        throw new ClassCastException("Object is not type of FilmDTO.class");
    }

    public FilmsListDTO castToFilmsListDTO(){
        if(this instanceof FilmsListDTO) {
            return (FilmsListDTO) this;
        }
        throw new ClassCastException("Object is not type of FilmsListDTO.class");
    }

    protected abstract void setAPI(API api);

    protected abstract void setUrl(String url);

    protected abstract URL getUrl();

//    public PeopleListDTO castToPeopleListDTO(){
//        if(this instanceof PeopleListDTO) {
//            return (PeopleListDTO) this;
//        }
//        throw new ClassCastException("Object is not type of PeopleListDTO.class");
//    }
//
//    public PersonDTO castToPersonDTO(){
//        if(this instanceof PersonDTO) {
//            return (PersonDTO) this;
//        }
//        throw new ClassCastException("Object is not type of PersonDTO.class");
//    }
//
//    public PlanetDTO castToPlanetDTO(){
//        if(this instanceof PlanetDTO) {
//            return (PlanetDTO) this;
//        }
//        throw new ClassCastException("Object is not type of PlanetDTO.class");
//    }
//
//    public PlanetsListDTO castToPlanetsListDTO(){
//        if(this instanceof PlanetsListDTO) {
//            return (PlanetsListDTO) this;
//        }
//        throw new ClassCastException("Object is not type of PlanetsListDTO.class");
//    }
//
//    public RootDTO castToRootDTO(){
//        if(this instanceof RootDTO) {
//            return (RootDTO) this;
//        }
//        throw new ClassCastException("Object is not type of RootDTO.class");
//    }
//
//    public SpeciesDTO castToSpeciesDTO(){
//        if(this instanceof SpeciesDTO) {
//            return (SpeciesDTO) this;
//        }
//        throw new ClassCastException("Object is not type of SpeciesDTO.class");
//    }
//
//    public SpeciesListDTO castToSpeciesListDTO(){
//        if(this instanceof SpeciesListDTO) {
//            return (SpeciesListDTO) this;
//        }
//        throw new ClassCastException("Object is not type of SpeciesListDTO.class");
//    }
//
//    public StarshipDTO castToStarshipDTO(){
//        if(this instanceof StarshipDTO) {
//            return (StarshipDTO) this;
//        }
//        throw new ClassCastException("Object is not type of StarshipDTO.class");
//    }
//
//    public StarshipsListDTO castToStarshipsListDTO(){
//        if(this instanceof StarshipsListDTO) {
//            return (StarshipsListDTO) this;
//        }
//        throw new ClassCastException("Object is not type of StarshipsListDTO.class");
//    }
//
//    public VehicleDTO castToVehicleDTO(){
//        if(this instanceof VehicleDTO) {
//            return (VehicleDTO) this;
//        }
//        throw new ClassCastException("Object is not type of VehicleDTO.class");
//    }
//
//    public VehiclesListDTO castToVehiclesListDTO(){
//        if(this instanceof VehiclesListDTO) {
//            return (VehiclesListDTO) this;
//        }
//        throw new ClassCastException("Object is not type of VehiclesListDTO.class");
//    }

}
