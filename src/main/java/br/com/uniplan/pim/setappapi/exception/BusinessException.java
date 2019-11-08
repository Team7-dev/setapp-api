package br.com.uniplan.pim.setappapi.exception;

public class BusinessException extends RuntimeException {

    private String messageKey;

    private String[] objects;

    public BusinessException(String messageKey) {
        this.messageKey = messageKey;
    }

    public BusinessException(String messageKey, String[] objects) {
        this.messageKey = messageKey;
        this.objects = objects;
    }

    public String getMessageKey() {
        return this.messageKey;
    }

    public String[] getObjects() {
        return objects;
    }

}
