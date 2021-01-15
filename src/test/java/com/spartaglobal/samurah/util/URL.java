package com.spartaglobal.samurah.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URL {

    private final String baseUrl;
    private String path;
    private String query;

    private URL(String baseUrl, String path, String query){
        if(baseUrl == null || baseUrl.isBlank()){
            throw new IllegalArgumentException("Base url should not be null or blank.");
        }
        this.baseUrl = baseUrl;
        if(path == null || path.isBlank()){
            this.path = null;
        }else{
            this.path = path.trim();
        }
        if(query == null || query.isBlank()){
            this.query = null;
        }else {
            this.query = query.trim();
        }
    }

    public URL appendToPath(String extension){
        path = path + extension;
        return this;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getPath() {
        return path;
    }

    public String getQuery() {
        return query;
    }

    public URL query(String value){
        this.query = ("?search="+value).replaceAll("\\s","%20");
        return this;
    }

    public static URL build(String baseUrl, String path, String query) {
        return new URL(baseUrl,path,query);
    }

    public static URL build(String baseUrl, String path){
        return build(baseUrl,path,"");
    }

    public static URL build(String baseUrl){
        return build(baseUrl,"","");
    }

    public static URL decode(String url){
        Pattern pattern = Pattern.compile(".?(?<protocol>http[s]?:[/]{2})?((?<hostname>\\w+)\\.)"+
                "?((?<domain>([\\w-]+)?\\.\\w+)/?)(?<path>[\\w/-]+)?(?<query>\\?[\\w=&]*)?(#(?<anchor>.*))?.?");
        Matcher matcher = pattern.matcher(url);
        if(!matcher.matches()){
            throw new IllegalArgumentException("Wrong URL format!");
        }
        StringBuilder baseUrl =  new StringBuilder();
        if(matcher.group("protocol")!=null){
            String protocol = matcher.group("protocol");
            if(!protocol.matches("http[s]?://")){
                throw new IllegalArgumentException("Wrong URL format.");
            }else{
                if(protocol.matches("http://")){
                    protocol="https://";
                }
                baseUrl.append(protocol);
            }
        }
        if(matcher.group("hostname")!=null){
            baseUrl.append(matcher.group("hostname"));
            baseUrl.append(".");
        }
        if(matcher.group("domain")!=null){
            baseUrl.append(matcher.group("domain"));
        }
        return build(baseUrl.toString(), matcher.group("path"), matcher.group("query"));
    }

    @Override
    public String toString() {
        StringBuilder urlBuild = new StringBuilder();
        urlBuild.append(baseUrl);
        if (baseUrl.charAt(baseUrl.length() - 1) != '/') {
            urlBuild.append("/");
        }
        if (path != null) {
            urlBuild.append(path);
            if(path.charAt(path.length()-1) != '/'){
                urlBuild.append("/");
            }
            if (query != null) {
                urlBuild.append(query);
            }
        }
        return urlBuild.toString();
    }
}
