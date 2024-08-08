package com.selaz.ms.input.rest.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.selaz.ms.core.exceptions.user.UserAlreadyExistsException;
import com.selaz.ms.core.exceptions.user.UserNotFoundException;
import com.selaz.ms.input.rest.dto.ApiResponseError;

@ControllerAdvice
public class UserRestExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponseError> handleUserNotFound(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseError(exception.getMessage(), HttpStatus.NOT_FOUND));
    }


    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponseError> handleUserAlreadExists(UserAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponseError(exception.getMessage(), HttpStatus.CONFLICT));
    }
}
