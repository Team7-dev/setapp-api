package br.com.uniplan.pim.setappapi.exception;

public class UniqueFieldContraintException extends RuntimeException {

    private String fieldName;

    public UniqueFieldContraintException(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

}
