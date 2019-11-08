package br.com.uniplan.pim.setappapi.exception;

public class FieldMustBeNullException extends RuntimeException {

    private String fieldName;

    public FieldMustBeNullException(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

}
