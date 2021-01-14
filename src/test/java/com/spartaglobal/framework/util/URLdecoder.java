package com.spartaglobal.framework.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLdecoder {
    private final String protocol;
    private final String hostname;
    private final String domain;
    private final String path;
    private final String parameters;
    private final String anchor;

    private URLdecoder(String protocol, String hostname, String domain, String path, String parameters, String anchor) {
        this.protocol = protocol;
        this.hostname = hostname;
        this.domain = domain;
        this.path = path;
        this.parameters = parameters;
        this.anchor = anchor;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getHostname() {
        return hostname;
    }

    public String getDomain() {
        return domain;
    }

    public String getPath() {
        return path;
    }

    public String getParameters() {
        return parameters;
    }

    public String getAnchor() {
        return anchor;
    }

    public static URLdecoder decode(String url){
        Pattern pattern = Pattern.compile("(?<protocol>http[s]?:[/]{2})?((?<hostname>\\w+)\\.)"+
                "?((?<domain>([\\w-]+)?\\.\\w+)/?)(?<path>[\\w/-]+)?(\\?(?<parameters>[\\w=&]*))?(#(?<anchor>.*))?");
        Matcher matcher = pattern.matcher(url);
        if(!matcher.matches()){
            throw new IllegalArgumentException("Wrong URL format!");
        }
        return new URLdecoder(matcher.group("protocol"),
                matcher.group("hostname"),
                matcher.group("domain"),
                matcher.group("path"),
                matcher.group("parameters"),
                matcher.group("anchor"));
    }
}
