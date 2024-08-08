package com.selaz.ms.input.rest.controller.user.dto;

import com.selaz.ms.core.domain.user.entity.Nivel;

public record UpdateUserRequestDTO(
    String username,
    Nivel nivel
) {
    
}
