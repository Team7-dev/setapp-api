package br.com.uniplan.pim.setappapi.exception;

public class DateParseException extends RuntimeException {

    private String dateString;

    private String fieldName;

    public DateParseException(String dateString, String fieldName) {
        this.dateString = dateString;
        this.fieldName = fieldName;
    }

    public String getDateString() {
        return dateString;
    }

    public String getFieldName() {
        return fieldName;
    }

}
