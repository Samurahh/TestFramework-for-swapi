package com.spartaglobal.framework.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spartaglobal.framework.dtos.SwapiObject;
import com.spartaglobal.framework.resourcetype.ResourceType;

public class BodyUtils {

    private final String body;
    private final SwapiObject swapiObject;

    public BodyUtils(String body, String url) throws JsonProcessingException {
        this.body = body;
        String path = URLdecoder.decode(url).getPath();
        this.swapiObject = Injector.inject(body, ResourceType.getClassOf(path));
    }

    public SwapiObject getObject(){
        return swapiObject;
    }

    public String getBody(){
        return body;
    }
}
