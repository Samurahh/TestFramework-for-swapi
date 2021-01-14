package com.spartaglobal.framework.util;

import java.net.http.HttpHeaders;

public class HeadersUtils {
    private final HttpHeaders httpHeaders;

    public HeadersUtils(HttpHeaders httpHeaders){
        this.httpHeaders = httpHeaders;
    }

    public boolean contains(String key, String expectedValue){
        boolean contains = false;
        for (String value: getHeaderValues(key)){
            if (value.equals(expectedValue)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    public boolean contains(String key){
        return getHeaderValues(key).length > 0;
    }

    public <T> boolean contains(String key, T expectedValue){
        return contains(key, String.valueOf(expectedValue));
    }

    public String[] getValues(String key){
        return getHeaderValues(key);
    }

    private String[] getHeaderValues(String key){
        return httpHeaders.allValues(key).toArray(new String[0]);
    }


}
