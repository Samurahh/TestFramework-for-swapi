package com.spartaglobal.framework.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spartaglobal.framework.Connection;

import java.net.http.HttpResponse;

public class ResponseUtils {

    private HeadersUtils headersUtils;
    private BodyUtils bodyUtils;

    public ResponseUtils(Connection connection) throws JsonProcessingException {
        this.headersUtils = new HeadersUtils(connection.getResponse().headers());
        this.bodyUtils = new BodyUtils(connection.getResponse().body(), connection.getURL());
    }

    public HeadersUtils headers() {
        return headersUtils;
    }

    public BodyUtils body() {
        return bodyUtils;
    }
}
