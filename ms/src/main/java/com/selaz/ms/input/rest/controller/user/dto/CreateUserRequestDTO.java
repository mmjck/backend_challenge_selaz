package com.selaz.ms.input.rest.controller.user.dto;

import com.selaz.ms.core.domain.user.entity.Nivel;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateUserRequestDTO(
    @Schema(name = "username", example = "maria123", required = true)
    String username,

    @Schema(name = "nivel", example = "ADMIN", required = true)
    Nivel nivel
) {
    
}
