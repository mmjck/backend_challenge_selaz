package com.selaz.ms.input.rest.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.selaz.ms.input.rest.controller.auth.dto.AuthRequestDTO;
import com.selaz.ms.input.rest.controller.auth.dto.AuthResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Auth", description = "auth endpoints")
public interface AuthApi {
    @Operation(summary = "Get token by username")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved"), 
        @ApiResponse(responseCode = "404", description = "Username not fount")
    })
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request);
} 
