package com.selaz.ms.repository.task.jpa.mapper;

import java.util.function.Function;

import com.selaz.ms.core.domain.task.entity.Task;
import com.selaz.ms.repository.task.jpa.model.TaskJpaModel;

public class TaskJpaModel2TaskMapper implements Function<TaskJpaModel, Task> {

    public static Task mapper(final TaskJpaModel model) {
        return new TaskJpaModel2TaskMapper().apply(model);
    }

    @Override
    public Task apply(TaskJpaModel model) {
        return Task
                .builder()
                .id(model.getId())
                .createdAt(model.getCreatedAt())
                .dueDate(model.getDueDate())
                .description(model.getDescription())
                .title(model.getTitle())
                .status(model.getStatus())
                .build();

    }

}
