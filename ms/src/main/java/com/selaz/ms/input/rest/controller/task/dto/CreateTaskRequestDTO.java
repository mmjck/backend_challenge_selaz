package com.selaz.ms.input.rest.controller.task.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NonNull;

public record CreateTaskRequestDTO(

    @Schema(name = "title", example = "Clean bedroom", required = true)
    @NonNull String title,


    @Schema(name = "description", example = "Clean bedroom today", required = true)
    @NonNull String description,

    @Schema(name = "due_date", example = "2024-08-08T02:21:52.562Z", required = true)
    @NonNull
    @JsonProperty(value = "due_date")
    LocalDateTime dueDate
) {
    
}
