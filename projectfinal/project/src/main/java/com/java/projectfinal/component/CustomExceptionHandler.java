package com.java.projectfinal.component;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

public class CustomExceptionHandler {

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public ErrorResponse handleMethodNotAllowed(Exception ex) {
        ErrorResponse response = new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), "Method not allowed");
        return response;
    }

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleBadRequestException(Exception ex) {
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad request");
        return response;
    }

    @ExceptionHandler(value = {NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleNotFoundException(Exception ex) {
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Not found");
        return response;
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponse handleUnauthorizedFoundException(Exception ex) {
        ErrorResponse response = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
        return response;
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleIllegalArgumentException(Exception ex) {
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Parameters you've entered are not correct");
        return response;
    }

    @ExceptionHandler(value = {PropertyReferenceException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handlePropertyReferenceException(Exception ex) {
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Sort field you've entered is not correct");
        return response;
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleDataIntegrityViolationException(Exception ex) {
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Invalid data to insert in the database");
        return response;
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleEntityNotFoundException(Exception ex) {
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "The UUID you've entered is wrong");
        return response;
    }
}
