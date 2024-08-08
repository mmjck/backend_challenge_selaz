package com.selaz.ms.input.rest.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.selaz.ms.input.rest.controller.user.dto.CreateUserRequestDTO;
import com.selaz.ms.input.rest.controller.user.dto.ListTaskResponseDTO;
import com.selaz.ms.input.rest.controller.user.dto.ListUserResponseDTO;
import com.selaz.ms.input.rest.controller.user.dto.UpdateUserRequestDTO;
import com.selaz.ms.input.rest.controller.user.dto.UserResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User", description = "The user endpoints")
public interface UserApi {
    @Operation(summary = "Create user")
    public ResponseEntity<UserResponseDTO> create(@RequestBody CreateUserRequestDTO request);

    @Operation(summary = "Update user by id")
    public ResponseEntity<UserResponseDTO> update(
            @PathVariable("id") Long id,
            @RequestBody UpdateUserRequestDTO request);

    @Operation(summary = "Get all users")
    public ResponseEntity<ListUserResponseDTO> listAll();

    @Operation(summary = "Get all tasks from user id")
    public ResponseEntity<ListTaskResponseDTO> listAll(
            @PathVariable("id") Long id);

    @Operation(summary = "Delete user", description="Delete user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")
    })
    public ResponseEntity<Void> delete(@PathVariable("id") Long id);
}
