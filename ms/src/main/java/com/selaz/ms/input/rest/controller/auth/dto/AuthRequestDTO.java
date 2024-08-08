package com.selaz.ms.input.rest.controller.auth.dto;

import io.micrometer.common.lang.NonNull;
import io.swagger.v3.oas.annotations.media.Schema;

public record AuthRequestDTO(
    @Schema(name = "username", example = "maria123", required = true)
    @NonNull String username
) {
    
}
