package gwr.library.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.core.JsonProcessingException;

import gwr.library.constant.CommonMessage;
import gwr.library.model.ErrorMessage;
import lombok.extern.log4j.Log4j2;

/**
 * The Class GlobalExceptionHandler.
 */
@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    /**
     * Handles ValidationExceptions from the rest controller.
     * 
     * @param e ValidationException
     * @return error response POJO
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<?> handleValidationException(HttpServletRequest request, ValidationException e) {
        log.info(e.getMessage(), e);

        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, LocalDateTime.now(),
                CommonMessage.RESOURCE_NOT_FOUND, e.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    /**
     * Handles JsonProcessingExceptions from the rest controller.
     * 
     * @param e JsonProcessingException
     * @return error response POJO
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = JsonProcessingException.class)
    public ResponseEntity<?> handleJsonProcessingException(HttpServletRequest request, JsonProcessingException e) {
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, LocalDateTime.now(),
                CommonMessage.RESOURCE_NOT_FOUND, e.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    /**
     * Handles IllegalArgumentExceptions from the rest controller.
     * 
     * @param e IllegalArgumentException
     * @return error response POJO
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException e) {
        log.info(e.getMessage(), e);

        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, LocalDateTime.now(),
                CommonMessage.RESOURCE_NOT_FOUND, e.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = UnsupportedOperationException.class)
    public ResponseEntity<?> handleUnsupportedOperationException(HttpServletRequest request,
            UnsupportedOperationException e) {
        log.info(e.getMessage(), e);

        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, LocalDateTime.now(),
                CommonMessage.RESOURCE_NOT_FOUND, e.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    /**
     * Handles MissingServletRequestParameterExceptions from the rest controller.
     * 
     * @param e MissingServletRequestParameterException
     * @return error response POJO
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMissingServletRequestParameterException(HttpServletRequest request,
            MissingServletRequestParameterException e) {
        log.info(e.getMessage(), e);

        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, LocalDateTime.now(),
                CommonMessage.RESOURCE_NOT_FOUND, e.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    /**
     * Handles NoHandlerFoundExceptions from the rest controller.
     * 
     * @param e NoHandlerFoundException
     * @return error response POJO
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseEntity<?> handleNoHandlerFoundException(HttpServletRequest request, NoHandlerFoundException e) {
        log.info(e.getMessage(), e);

        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, LocalDateTime.now(),
                CommonMessage.RESOURCE_NOT_FOUND, e.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    /**
     * Handle unwanted exception.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleUnwantedException(Exception ex, WebRequest request) {
        log.error(ex);
        ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now(),
                CommonMessage.INTERNAL_SERVER_ERROR, ex.toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }
}
