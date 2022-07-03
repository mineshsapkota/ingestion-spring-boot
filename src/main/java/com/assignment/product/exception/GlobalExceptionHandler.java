package com.assignment.product.exception;

import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    protected ResponseEntity < Object > handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {

        String ERROR_MESSAGE = methodArgumentNotValidException.getMessage();

        try {
            ERROR_MESSAGE = methodArgumentNotValidException.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        } catch (Exception e) {
            logger.error("Error constructing error message", e);
        }
        logger.error(ERROR_MESSAGE, methodArgumentNotValidException);
        ErrorDetails errorResponse = getErrorResponse(HttpStatus.BAD_REQUEST, ERROR_MESSAGE, webRequest);
        return handleExceptionInternal(methodArgumentNotValidException, errorResponse, headers, HttpStatus.BAD_REQUEST, webRequest);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity < Object > handleIllegalArgumentException(IllegalArgumentException exception, WebRequest webRequest) {
        final String ERROR_MESSAGE = exception.getMessage();
        logger.error(ERROR_MESSAGE, exception);
        ErrorDetails errorResponse = getErrorResponse(HttpStatus.BAD_REQUEST, ERROR_MESSAGE, webRequest);
        return handleExceptionInternal(exception, errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity < Object > handleAnyException(Exception exception, WebRequest webRequest) {
        final String ERROR_MESSAGE = "An unexpected error occurred";
        logger.error(ERROR_MESSAGE, exception);
        ErrorDetails errorResponse = getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_MESSAGE, webRequest);
        return handleExceptionInternal(exception, errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }
   
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity <?> resourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
    	
    	final String ERROR_MESSAGE = exception.getMessage();
        logger.error(ERROR_MESSAGE, exception);
    	ErrorDetails errorResponse = getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_MESSAGE, webRequest);
        return new ResponseEntity < > (errorResponse, HttpStatus.NOT_FOUND);
    }
    
    private ErrorDetails getErrorResponse(HttpStatus status, String errorMessage, WebRequest request) {
        if (errorMessage.isEmpty()) {
            errorMessage = "An unexpected error occurred";
        }
        return new ErrorDetails(status.value(), new Date(), errorMessage, request.getDescription(false));
    }
}