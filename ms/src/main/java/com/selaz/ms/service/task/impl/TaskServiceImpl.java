package com.selaz.ms.service.task.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.selaz.ms.core.domain.task.entity.Status;
import com.selaz.ms.core.domain.task.entity.Task;
import com.selaz.ms.core.domain.task.gateway.TaskGateway;
import com.selaz.ms.service.task.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskGateway gateway;

    public TaskServiceImpl(TaskGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Task create(String title, String description, LocalDateTime dueDate, Long userId) {
        var model = Task
                .builder()
                .userId(userId)
                .title(title)
                .userId(userId)
                .description(description)
                .dueDate(dueDate)
                .status(Status.EM_ANDAMENTO)
                .build();

        var response = this.gateway.save(model);
        return response;
    }

    @Override
    public Task update(Long id, String title, String description, Status status, LocalDateTime dueDate) {
        var model = Task
                .builder()
                .title(title)
                .description(description)
                .dueDate(dueDate)
                .status(status)
                .build();

        var response = this.gateway.update(id, model);
        return response;
    }

    @Override
    public List<Task> getAllTaskByUserId(Long id, Status status, Boolean orderByDueDate) {
        var response = this.gateway.findByUserId(id, status, orderByDueDate);
        return response;
    }


    @Override
    public void delete(Long id) {
        this.gateway.delete(id);
    }

}
