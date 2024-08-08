package com.selaz.ms.repository.task.jpa.mapper;

import java.util.function.Function;

import com.selaz.ms.core.domain.task.entity.Task;
import com.selaz.ms.repository.task.jpa.model.TaskJpaModel;

public class Task2TaskJpaModelMapper implements Function<Task, TaskJpaModel> {

    public static TaskJpaModel mapper(final Task model) {
        return new Task2TaskJpaModelMapper().apply(model);
    }

    @Override
    public TaskJpaModel apply(Task model) {
        return TaskJpaModel.builder()
                .description(model.getDescription())
                .title(model.getTitle())
                .dueDate(model.getDueDate())
                .userId(model.getUserId())
                .status(model.getStatus())
                .build();

    }

}
