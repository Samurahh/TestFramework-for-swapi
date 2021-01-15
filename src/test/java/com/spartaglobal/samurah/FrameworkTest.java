package com.spartaglobal.samurah;

import com.spartaglobal.samurah.util.API;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class FrameworkTest {

    @Test
    @DisplayName("Test URL")
    void testUrl() throws Exception{
        System.out.println(API.client.root().calculateTotalFiles());
    }
}
