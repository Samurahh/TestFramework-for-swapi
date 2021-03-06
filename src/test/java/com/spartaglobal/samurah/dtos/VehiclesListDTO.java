package com.spartaglobal.samurah.dtos;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spartaglobal.samurah.interfaces.ListInterface;
import com.spartaglobal.samurah.util.API;
import com.spartaglobal.samurah.util.URL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class VehiclesListDTO extends SwapiObject implements ListInterface {

    private int count;
    private String next;
    private String previous;
    private VehicleDTO[] results;
    private API api = API.client;
    private String url;
    public VehiclesListDTO() {
    }

    public static VehiclesListDTO createFrom(JsonObject value) {
        return new Gson().fromJson(value.toString(), VehiclesListDTO.class);
    }

    public static VehiclesListDTO createFrom(JsonObject value, API api) {
        VehiclesListDTO vehiclesListDTO = new Gson().fromJson(value.toString(), VehiclesListDTO.class);
        vehiclesListDTO.setAPI(api);
        return vehiclesListDTO;
    }

    public static VehiclesListDTO search(String searchName, API api) throws Exception {
        URL queryUrl = api.root().vehicles().getUrl().query(searchName);
        VehiclesListDTO vehiclesListDTO = VehiclesListDTO.createFrom(api.request(queryUrl), api);
        vehiclesListDTO.setUrl(queryUrl.toString());
        return vehiclesListDTO;
    }

    @Override
    public VehiclesListDTO next() throws Exception {
        if (this.next != null) {
            return VehiclesListDTO.createFrom(api.request(this.next), api);
        }
        return null;
    }

    @Override
    public VehiclesListDTO previous() throws Exception {
        if (this.previous != null) {
            return VehiclesListDTO.createFrom(api.request(this.previous), api);
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return this.next != null;
    }

    @Override
    public boolean hasPrevious() {
        return this.previous != null;
    }

    @Override
    protected void setAPI(API api) {
        this.api = api;
    }

    @Override
    public URL getUrl() {
        return URL.decode(url);
    }

    @Override
    protected void setUrl(String url) {
        this.url = url;
    }

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public VehicleDTO[] getResults() {
        return results;
    }

    public Collection<VehicleDTO> getAll(){
        ArrayList<VehicleDTO> all = new ArrayList<>(Arrays.asList(results));
        if(this.hasPrevious()) {
            try {
                addToListPrevious(all, this.previous());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(this.hasNext()) {
            try {
                addToListNext(all, this.next());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return all;
    }

    private void addToListPrevious(Collection<VehicleDTO> list, VehiclesListDTO peopleListDTO){
        list.addAll(Arrays.asList(peopleListDTO.results));
        if(peopleListDTO.hasPrevious()) {
            try {
                addToListPrevious(list, peopleListDTO.previous());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void addToListNext(Collection<VehicleDTO> list, VehiclesListDTO peopleListDTO){
        list.addAll(Arrays.asList(peopleListDTO.results));
        if(peopleListDTO.hasNext()) {
            try {
                addToListNext(list, peopleListDTO.next());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
