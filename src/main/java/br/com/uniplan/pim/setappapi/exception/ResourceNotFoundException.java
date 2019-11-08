package br.com.uniplan.pim.setappapi.exception;

public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;

    private String resourceValue;

    public ResourceNotFoundException(String resourceName, String resourceValue) {
        this.resourceName = resourceName;
        this.resourceValue = resourceValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getResourceValue() {
        return resourceValue;
    }

}
