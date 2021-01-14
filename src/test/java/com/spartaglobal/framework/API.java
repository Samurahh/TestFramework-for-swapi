package com.spartaglobal.framework;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

public class API {
    public static final String BASE_URL = "https://swapi.dev/";
    private final LinkedList<Connection> connections;

    public API(){
        connections = new LinkedList<>();
    }

    public Connection connectTo(String url) throws IOException, InterruptedException {
        Connection newConnection = Connection.connect(url);
        connections.addFirst(newConnection);
        return newConnection;
    }

    public Collection<Connection> getConnectionHistory(){
        return connections;
    }

    public Connection getLastConnection(){
        return connections.peek();
    }

    public Connection getFirstConnection(){
        return connections.peekLast();
    }

}
