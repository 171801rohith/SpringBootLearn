package com.restapi.MyRestAPI.exceptions;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(Integer id) {
        super("Author with ID: " + id + " not found in the Database.");
    }
}
