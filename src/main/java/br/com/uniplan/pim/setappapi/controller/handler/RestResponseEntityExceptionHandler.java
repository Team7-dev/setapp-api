package br.com.uniplan.pim.setappapi.controller.handler;

import br.com.uniplan.pim.setappapi.exception.*;
import br.com.uniplan.pim.setappapi.exception.BusinessException;
import br.com.uniplan.pim.setappapi.exception.DateParseException;
import br.com.uniplan.pim.setappapi.exception.FieldCannotBeNullException;
import br.com.uniplan.pim.setappapi.exception.FieldMustBeNullException;
import br.com.uniplan.pim.setappapi.exception.ResourceCannotBeNullException;
import br.com.uniplan.pim.setappapi.exception.ResourceNotFoundException;
import br.com.uniplan.pim.setappapi.exception.UniqueFieldContraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @Autowired
    public RestResponseEntityExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(Exception e) {
        return new ResponseEntity<>("Access denied message here", new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ResourceCannotBeNullException.class})
    public ResponseEntity<Object> handleResourceCannotBeNullException() {
        String message = messageSource.getMessage("resource.cannot.be.null", null, LocaleContextHolder.getLocale());
        return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({FieldMustBeNullException.class})
    public ResponseEntity<Object> handleFieldMustBeNullException(FieldMustBeNullException e) {
        String[] objects = new String[]{e.getFieldName()};
        String message = messageSource.getMessage("field.must.be.null", objects, LocaleContextHolder.getLocale());
        return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({FieldCannotBeNullException.class})
    public ResponseEntity<Object> handleFieldCannotBeNullException(FieldCannotBeNullException e) {
        String[] objects = new String[]{e.getFieldName()};
        String message = messageSource.getMessage("field.cannot.be.null", objects, LocaleContextHolder.getLocale());
        return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e) {
        String[] objects = new String[]{e.getResourceName(), e.getResourceValue()};
        String message = messageSource.getMessage("resource.not.found", objects, LocaleContextHolder.getLocale());
        return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UniqueFieldContraintException.class})
    public ResponseEntity<Object> handleUniqueFieldContraintException(UniqueFieldContraintException e) {
        String[] objects = new String[]{e.getFieldName()};
        String message = messageSource.getMessage("field.must.be.unique", objects, LocaleContextHolder.getLocale());
        return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<Object> handleBusinessException(BusinessException e) {
        String message = messageSource.getMessage(e.getMessageKey(), e.getObjects(), LocaleContextHolder.getLocale());
        return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DateParseException.class})
    public ResponseEntity<Object> handleDateParseException(DateParseException e) {
        String[] objects = new String[]{e.getDateString(), e.getFieldName()};
        String message = messageSource.getMessage("date.parse.error", objects, LocaleContextHolder.getLocale());
        return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
