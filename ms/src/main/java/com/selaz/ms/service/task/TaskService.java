package com.selaz.ms.service.task;

import java.time.LocalDateTime;
import java.util.List;

import com.selaz.ms.core.domain.task.entity.Status;
import com.selaz.ms.core.domain.task.entity.Task;

public interface TaskService {
    public Task create(String title, String description, LocalDateTime dueDate, Long userId);
    public Task update(Long id, String title, String description, Status status, LocalDateTime dueDate);
    public void delete(Long id);
    public List<Task> getAllTaskByUserId(Long id, Status status, Boolean orderByDueDate);
}
