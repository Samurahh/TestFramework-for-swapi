package com.spartaglobal.framework.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spartaglobal.framework.Connection;
import com.spartaglobal.framework.dtos.SwapiObject;
import com.spartaglobal.framework.resourcetype.ResourceType;
import com.spartaglobal.framework.util.BodyUtils;

public class Injector {


    public static <T> T inject(String body, Class<T> valueType) throws JsonProcessingException {
        return createObject(body,valueType);
    }

    public static <T> T inject(BodyUtils body, Class<T> valueType) throws JsonProcessingException {
        return createObject(body.getBody(), valueType);
    }

    private static <T> T createObject(String body, Class<T> valueType) throws JsonProcessingException {
        return new ObjectMapper().readValue(body, valueType);
    }

}
