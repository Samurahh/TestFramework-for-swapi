package com.spartaglobal.framework.dtos;

import java.util.ArrayList;

public class VehiclesListDTO extends SwapiObject {

    public VehiclesListDTO(){}

    private int count;
    private String next;
    private String previous;
    private VehicleDTO[] results;

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

    public VehicleDTO[] getResults() {
        return results;
    }

    public void setResults(VehicleDTO[] results) {
        this.results = results;
    }
}
