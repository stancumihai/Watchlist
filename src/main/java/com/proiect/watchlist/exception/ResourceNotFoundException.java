package com.proiect.watchlist.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String theClass, Integer id) {
        super("Could not find id: " + id + " for " + theClass);
    }
}