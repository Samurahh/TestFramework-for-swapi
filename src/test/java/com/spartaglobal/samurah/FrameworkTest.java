package com.spartaglobal.samurah;

import com.spartaglobal.samurah.dtos.FilmDTO;
import com.spartaglobal.samurah.dtos.PeopleListDTO;
import com.spartaglobal.samurah.dtos.RootDTO;
import com.spartaglobal.samurah.util.API;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class FrameworkTest {

    @Test
    @DisplayName("Test URL")
    void testUrl() throws Exception{
        RootDTO root = RootDTO.createFrom(API.client);
        FilmDTO film = root.films().getResults()[0];
        System.out.println(PeopleListDTO.search("b", API.client).getCount());
        System.out.println(PeopleListDTO.search("b", API.client).getCount());
    }
}
