package br.com.uniplan.pim.setappapi.exception;

public class FieldCannotBeNullException extends RuntimeException {

    private String fieldName;

    public FieldCannotBeNullException(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

}
