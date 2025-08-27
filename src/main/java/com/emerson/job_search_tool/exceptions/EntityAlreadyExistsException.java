package com.emerson.job_search_tool.exceptions;

public class EntityAlreadyExistsException extends RuntimeException {
    
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
