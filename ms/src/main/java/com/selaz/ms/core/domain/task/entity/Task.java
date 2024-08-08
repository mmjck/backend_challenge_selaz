package com.selaz.ms.core.domain.task.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Long id;
    private Status status;

    private Long userId;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime dueDate;

}
