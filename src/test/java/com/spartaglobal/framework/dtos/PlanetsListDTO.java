package com.spartaglobal.framework.dtos;

import java.util.ArrayList;

public class PlanetsListDTO extends SwapiObject {

    public PlanetsListDTO(){}

    private int count;
    private String next;
    private String previous;
    private PlanetDTO[] results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public PlanetDTO[] getResults() {
        return results;
    }

    public void setResults(PlanetDTO[] results) {
        this.results = results;
    }

}
