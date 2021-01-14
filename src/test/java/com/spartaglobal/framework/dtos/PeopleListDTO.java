package com.spartaglobal.framework.dtos;

import java.util.ArrayList;
import java.util.HashMap;

public class PeopleListDTO extends SwapiObject {

    public PeopleListDTO(){
    }

    private int count;
    private String next;
    private String previous;
    private PersonDTO[] results;

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

    public PersonDTO[] getResults() {
        return results;
    }

    public void setResults(PersonDTO[] results) {
        this.results = results;
    }
}
