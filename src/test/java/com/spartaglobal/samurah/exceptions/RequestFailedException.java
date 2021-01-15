package com.spartaglobal.samurah.exceptions;


import com.spartaglobal.samurah.util.Connection;

public class RequestFailedException extends Exception{
    private Connection connection;

    public RequestFailedException(){
        super();
    }

    public RequestFailedException(Connection connection){
        super();
        this.connection = connection;
    }

    public RequestFailedException(Connection connection, String message){
        super(message);
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
