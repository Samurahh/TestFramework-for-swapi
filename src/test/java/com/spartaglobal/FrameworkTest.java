package com.spartaglobal;

import com.spartaglobal.framework.API;
import com.spartaglobal.framework.Connection;
import com.spartaglobal.framework.dtos.PeopleListDTO;
import com.spartaglobal.framework.dtos.PersonDTO;
import com.spartaglobal.framework.dtos.SwapiObject;
import com.spartaglobal.framework.resourcetype.ResourceType;
import com.spartaglobal.framework.util.Injector;
import com.spartaglobal.framework.util.ResponseUtils;
import com.spartaglobal.framework.util.URLdecoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class FrameworkTest {

    private API api;
    SwapiObject swapiObject;

    @BeforeEach
    void setup() throws IOException, InterruptedException {
        api = new API();
        api.connectTo("https://swapi.dev/api/people/");
        ResponseUtils responseUtils = new ResponseUtils(api.getLastConnection());
        swapiObject = Injector.inject(responseUtils.body().getBody(), ResourceType.getClassOf(URLdecoder.decode(api.getLastConnection().getURL()).getPath()));
    }

    @Test
    @DisplayName("Testing")
    void testing() throws IOException, InterruptedException {

    }


    @Test
    @DisplayName("Go thru each element of the list and check their url to see if they are the same")
    void goThruEachElementOfTheListAndCheckTheirUrlToSeeIfTheyAreTheSame() throws IOException, InterruptedException {
        PeopleListDTO peopleListDTO = (PeopleListDTO) swapiObject;
        PersonDTO[] personDTOS = peopleListDTO.getResults();
        for (PersonDTO personDTO : personDTOS) {
            String url = personDTO.getUrl();
            url = url.replace("http","https");
            api.connectTo(url);
            PersonDTO currentPerson = (PersonDTO) new ResponseUtils(api.getLastConnection()).body().getObject();
            System.out.println("Comparing "+ personDTO.getName() + " with "+ currentPerson.getName());
        }
    }
    
}
