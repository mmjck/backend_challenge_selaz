package com.selaz.ms.input.rest.controller.user.dto;

import java.util.List;

import com.selaz.ms.input.rest.controller.task.dto.TaskResponseDTO;
public record ListTaskResponseDTO(
    int total,
    List<TaskResponseDTO> data
) {
    
}
