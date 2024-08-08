package com.selaz.ms.input.rest.controller.user.dto;

import com.selaz.ms.core.domain.user.entity.Nivel;

public record UserResponseDTO(
    Long id,
    String username,
    Nivel nivel
) {
    
}
