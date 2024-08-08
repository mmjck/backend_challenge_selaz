package com.selaz.ms.input.rest.dto;

import org.springframework.http.HttpStatus;

public record ApiResponseError(
    String message,
    HttpStatus status
) {
    
}
