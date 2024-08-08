package com.selaz.ms.input.rest.controller.user.dto;

import java.util.List;

public record ListUserResponseDTO(
    int total,
    List<UserResponseDTO> data
) {
    
}
