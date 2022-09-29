package com.ezyfox.cvconnect.exception;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    private final String resource;

    public ResourceNotFoundException(String resource) {
        this(resource, resource + " not found");
    }

    public ResourceNotFoundException(String resource, String message) {
        super(message);
        this.resource = resource;
    }

    public Map<String, String> getResponseData() {
        return Collections.singletonMap(resource, " not found");
    }
}
