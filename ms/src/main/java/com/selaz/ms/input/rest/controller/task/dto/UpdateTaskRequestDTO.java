package com.selaz.ms.input.rest.controller.task.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.selaz.ms.core.domain.task.entity.Status;

public record UpdateTaskRequestDTO(
    String title,
    String description,

    
    Status status,

    @JsonProperty(value = "due_date")
    LocalDateTime dueDate
) {
    
}
